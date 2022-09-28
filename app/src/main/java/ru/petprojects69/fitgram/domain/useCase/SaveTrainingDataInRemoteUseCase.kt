package ru.petprojects69.fitgram.domain.useCase

import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.ui.MainActivity

class SaveTrainingDataInRemoteUseCase(
    private val cloudDb: FirebaseFirestore,
) : KoinComponent {
    private val preferences: SharedPreferences by inject()

    fun execute(trainings: MutableList<TrainingEntity>?): List<Task<Void>> {
        val userId = preferences.getString(MainActivity.PREF_USER_ID_KEY, null)
        val listTask = mutableListOf<Task<Void>>()
        if (userId != null) {
            trainings?.forEach { training ->
                listTask.add(
                    cloudDb.collection(userId)
                        .document(training.id.toString())
                        .set(training)
                )
            }
        }
        return listTask
    }
}