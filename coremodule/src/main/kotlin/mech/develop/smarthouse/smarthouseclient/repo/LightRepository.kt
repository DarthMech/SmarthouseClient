package mech.develop.smarthouse.smarthouseclient.repo

interface LightRepository {
    suspend fun getBedroomState(): Boolean

    suspend fun getLibraryLedState(): Boolean

    suspend fun setBedroomLedState(state: Boolean)

    suspend fun setLibraryLedState(state: Boolean)

    suspend fun setTapeLedState(state: Boolean)

    suspend fun setBlueLedStripState(progress: Int)
    suspend fun setGreenLedStripState(progress: Int)
    suspend fun setRedLedStripState(progress: Int)

    suspend fun turnOffAll()
}