package mech.develop.smarthouse.smarthouseclient.presentation.activity

interface SmarthouseMainView {

    fun setBedroomCheckState(state: Boolean)
    fun setLibraryCheckState(state: Boolean)
    fun setTapeLedCheckState(state: Boolean)

    fun clearControls()
}