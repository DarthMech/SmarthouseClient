package mech.develop.smarthouse.smarthouseclient.lightresetusecase.domain.interactor

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mech.develop.smarthouse.smarthouseclient.coremodule.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.coremodule.repo.LightRepository
import javax.inject.Inject

@ActivityScope
class LightResetInteractor @Inject constructor(private val repo: LightRepository) {
    fun turnOffAll() {
        GlobalScope.launch {
            repo.turnOffAll()
        }
    }

    suspend fun turnOffAllAwait() {
        GlobalScope.async {
            repo.turnOffAll()
        }.await()
    }

}