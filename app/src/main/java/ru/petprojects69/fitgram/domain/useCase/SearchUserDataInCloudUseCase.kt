package ru.petprojects69.fitgram.domain.useCase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.getField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.data.entity.UserEntityRemote
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.utils.awaitTask

class SearchUserDataInCloudUseCase(
    private val cloudDb: FirebaseFirestore,
    private val coroutineScope: CoroutineScope
) {
    suspend fun execute(userId: String?): UserEntity? {
        var user: UserEntity? = null
        if (userId != null) {
            val task = cloudDb.collection(userId).get()
            val mainJob = coroutineScope.launch {
                kotlin.runCatching {
                    awaitTask(task)
                }
                    .onSuccess { querySnapshot ->
                        user = searchDocument(userId, querySnapshot)
                    }
                    .onFailure {
                        user = null
                    }
            }
            mainJob.join()
        }
        return user
    }


    private suspend fun searchDocument(
        userId: String,
        querySnapshot: QuerySnapshot
    ): UserEntity? {
        var user: UserEntity? = null
        var documentCreated = false
        for (doc in querySnapshot.documentChanges) {
            val searchDocTask: Task<DocumentSnapshot> =
                cloudDb.collection(userId).document(doc.document.id).get()

            val job = coroutineScope.launch {
                kotlin.runCatching {
                    awaitTask(searchDocTask)
                }
                    .onSuccess { doc ->
                        documentCreated = checkingExistingDocument(doc)
                        if (documentCreated) {
                            user = UserEntity(
                                id = doc.getString("id") ?: "id",
                                email = doc.getString("email") ?: "email",
                                sex = doc.getBoolean("sex") ?: true,
                                name = doc.getString("name"),
                                surname = doc.getString("surname"),
                                rank = doc.getField("rank"),
                                age = doc.getField("age"),
                                weight = doc.getField("weight"),
                                height = doc.getField("height"),
                                calories = doc.getField("calories"),
                                completed = doc.getBoolean("completed") ?: false
                            )
                        }
                    }
                    .onFailure {
                        user = null
                    }
            }
            job.join()
            if (documentCreated) {
                break
            }
        }
        return user
    }

    private fun checkingExistingDocument(
        documentSnapshot: DocumentSnapshot
    ): Boolean {
        return (documentSnapshot.get("typeEntity") == UserEntityRemote.USER_DATA_ENTITY_KEY)
    }
}