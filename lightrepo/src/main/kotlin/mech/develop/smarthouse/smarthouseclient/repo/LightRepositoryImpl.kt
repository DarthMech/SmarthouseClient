package mech.develop.smarthouse.smarthouseclient.repo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi

class LightRepositoryImpl(private val api: SmarthouseRestApi) : LightRepository {

    override suspend fun getBedroomState(): Boolean =
            GlobalScope.async {
                api.getBedroomLedState().execute().body() ?: false
            }.await()

    override suspend fun getLibraryLedState(): Boolean =
            GlobalScope.async {
                api.getLibraryLedState().execute().body() ?: false
            }.await()

    override suspend fun setBedroomLedState(state: Boolean) {
        api.setBedroomState(state).execute()
    }

    override suspend fun setLibraryLedState(state: Boolean) {
        api.setLibraryLedState(state).execute()
    }

    override suspend fun setTapeLedState(state: Boolean) {
        if (state) {
            api.setRedLedStripState(255).execute()
            api.setGreenLedStripState(255).execute()
            api.setBlueLedStripState(255).execute()
        } else {
            api.setRedLedStripState(0).execute()
            api.setGreenLedStripState(0).execute()
            api.setBlueLedStripState(0).execute()
        }
    }

    override suspend fun setBlueLedStripState(progress: Int) {
        api.setBlueLedStripState(progress).execute()
    }

    override suspend fun setGreenLedStripState(progress: Int) {
        api.setGreenLedStripState(progress).execute()
    }

    override suspend fun setRedLedStripState(progress: Int) {
        api.setRedLedStripState(progress).execute()
    }

    override suspend fun turnOffAll() {
        api.turnOffAll().execute()
    }

}