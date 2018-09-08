package mech.develop.smarthouse.smarthouseclient.domain.interactor

import kotlinx.coroutines.experimental.launch
import mech.develop.smarthouse.smarthouseclient.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.repo.LightRepository
import javax.inject.Inject

@ActivityScope
class LightControlInteractor @Inject constructor(private val repo: LightRepository) {

    suspend fun getBedroomState(): Boolean = repo.getBedroomState()

    suspend fun getLibraryLedState(): Boolean = repo.getLibraryLedState()

    fun setBedroomLedState(state: Boolean) {
        launch {
            repo.setBedroomLedState(state)
        }
    }

    fun setLibraryLedState(state: Boolean) {
        launch {
            repo.setLibraryLedState(state)
        }
    }

}