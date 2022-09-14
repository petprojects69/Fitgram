package ru.petprojects69.fitgram.domain.useCase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.data.entity.UserEntityRemote
import ru.petprojects69.fitgram.data.entity.toMap
import ru.petprojects69.fitgram.data.entity.toUserEntityRemote
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.initData.SaveUserDataState
import ru.petprojects69.fitgram.ui.utils.awaitTask

class SaveUserDataInCloudUseCase(
    private val cloudDb: FirebaseFirestore,
    private val coroutineScope: CoroutineScope
) {
    suspend fun execute(user: UserEntity): SaveUserDataState {
        var state: SaveUserDataState = SaveUserDataState.Loading
        val task = cloudDb.collection(user.id).get()
        val mainJob = coroutineScope.launch {
            kotlin.runCatching {
                awaitTask(task)
            }
                .onSuccess { querySnapshot ->
                    state = searchDocument(user, querySnapshot)
                }
                .onFailure {
                    state = SaveUserDataState.Error(it)
                }
        }
        mainJob.join()
        return state
    }


    private suspend fun searchDocument(
        user: UserEntity,
        querySnapshot: QuerySnapshot
    ): SaveUserDataState {
        var documentCreated = false
        var state: SaveUserDataState = SaveUserDataState.Loading
        for (doc in querySnapshot.documentChanges) {
            val searchDocTask: Task<DocumentSnapshot> =
                cloudDb.collection(user.id).document(doc.document.id).get()

            val job = coroutineScope.launch {
                kotlin.runCatching {
                    awaitTask(searchDocTask)
                }
                    .onSuccess { doc ->
                        documentCreated = checkingExistingDocument(user, doc)
                        if (documentCreated) {
                            state = SaveUserDataState.Success(doc.id)
                        }
                    }
                    .onFailure {
                        state = SaveUserDataState.Error(it)
                    }
            }
            job.join()
            if (documentCreated) {
                break
            }
        }
        if (!documentCreated) {
            state = addNewUserDataDocument(user)
            Log.d("Debug", "$state")
        }
        return state
    }

    private fun checkingExistingDocument(
        user: UserEntity,
        documentSnapshot: DocumentSnapshot
    ): Boolean {
        return (if (documentSnapshot.get("typeEntity") == UserEntityRemote.USER_DATA_ENTITY_KEY) {
            changeUserDataDocument(user, documentSnapshot.id)
            true
        } else {
            false
        })
    }

    private suspend fun addNewUserDataDocument(user: UserEntity): SaveUserDataState {
        var state: SaveUserDataState = SaveUserDataState.Loading
        val task = cloudDb.collection(user.id)
            .add(user.toUserEntityRemote())
        val job = coroutineScope.launch {
            kotlin.runCatching {
                awaitTask(task)
            }
                .onSuccess { document ->
                    state = SaveUserDataState.Success(document.id)
                }
                .onFailure { e ->
                    state = SaveUserDataState.Error(e)
                }
        }
        job.join()
        return state
    }

    private fun changeUserDataDocument(user: UserEntity, documentId: String) {
        cloudDb.collection(user.id)
            .document(documentId)
            .update(user.toUserEntityRemote().toMap())
            .addOnSuccessListener {
                Log.d("Debug", "Update")
            }
            .addOnFailureListener { e ->
                Log.d("Debug", e.message.toString())
            }
    }
}