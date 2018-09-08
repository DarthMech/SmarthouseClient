package mech.develop.smarthouse.smarthouseclient.domain.interactor

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import mech.develop.smarthouse.smarthouseclient.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.repo.LightRepository
import javax.inject.Inject

@ActivityScope
class LightResetInteractor @Inject constructor(private val repo: LightRepository) {
    fun turnOffAll() {
        launch {
            repo.turnOffAll()
        }
    }

    suspend fun turnOffAllAwait() {
        async {
            repo.turnOffAll()
        }.await()
    }

}