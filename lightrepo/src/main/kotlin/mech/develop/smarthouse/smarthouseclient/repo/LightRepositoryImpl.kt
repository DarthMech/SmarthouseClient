package mech.develop.smarthouse.smarthouseclient.repo

import kotlinx.coroutines.experimental.async
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi

class LightRepositoryImpl(private val api: SmarthouseRestApi) : LightRepository {

    override suspend fun getBedroomState(): Boolean =
            async {
                api.getBedroomLedState().execute().body() ?: false
            }.await()

    override suspend fun getLibraryLedState(): Boolean =
            async {
                api.getLibraryLedState().execute().body() ?: false
            }.await()

    override suspend fun setBedroomLedState(state: Boolean) {
        api.setBedroomState(state).execute()
    }

    override suspend fun setLibraryLedState(state: Boolean) {
        api.setLibraryLedState(state).execute()
    }

    override suspend fun turnOffAll() {
        api.turnOffAll().execute()
    }

}