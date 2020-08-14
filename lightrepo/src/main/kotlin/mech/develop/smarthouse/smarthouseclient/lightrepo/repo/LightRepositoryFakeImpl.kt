package mech.develop.smarthouse.smarthouseclient.lightrepo.repo

import mech.develop.smarthouse.smarthouseclient.coremodule.repo.LightRepository

class LightRepositoryFakeImpl :
        LightRepository {
    override suspend fun getBedroomState(): Boolean = false

    override suspend fun getLibraryLedState(): Boolean  = false

    override suspend fun setBedroomLedState(state: Boolean) {
    }

    override suspend fun setLibraryLedState(state: Boolean) {
    }

    override suspend fun setTapeLedState(state: Boolean) {
    }

    override suspend fun setBlueLedStripState(progress: Int) {
    }

    override suspend fun setGreenLedStripState(progress: Int) {
    }

    override suspend fun setRedLedStripState(progress: Int) {
    }

    override suspend fun turnOffAll() {
    }

}