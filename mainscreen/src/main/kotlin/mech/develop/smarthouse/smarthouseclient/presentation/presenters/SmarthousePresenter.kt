package mech.develop.smarthouse.smarthouseclient.presentation.presenters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightControlInteractor
import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightResetInteractor
import mech.develop.smarthouse.smarthouseclient.presentation.activity.SmarthouseMainView
import java.lang.ref.WeakReference

class SmarthousePresenter(private val lightControl: LightControlInteractor,
                          private val lightReset: LightResetInteractor) {

    private lateinit var viewRef: WeakReference<SmarthouseMainView>

    fun onAttach(view: SmarthouseMainView) {
        viewRef = WeakReference(view)
    }

    fun onDetach() {
        viewRef.clear()
    }

    fun onStart() {
        GlobalScope.launch(Dispatchers.Main) {
            viewRef.get()?.setBedroomCheckState(lightControl.getBedroomState())
            viewRef.get()?.setLibraryCheckState(lightControl.getLibraryLedState())
        }
    }

    fun setBedroomLedState(state: Boolean) {
        lightControl.setBedroomLedState(state)
    }

    fun setLibraryLedState(state: Boolean) {
        lightControl.setLibraryLedState(state)
    }

    fun setTapeLedState(state: Boolean) {
        lightControl.setTapeLedState(state)
    }

    // led
    fun setBlueLedStripState(progress: Int) {
        lightControl.setBlueLedStripState(progress)
    }

    fun setGreenLedStripState(progress: Int) {
        lightControl.setGreenLedStripState(progress)
    }

    fun setRedLedStripState(progress: Int) {
        lightControl.setRedLedStripState(progress)
    }

    // reset
    fun killAll() {
        reset()
    }

    // ------------------------------ Inner methods -----------------------------------------------
    private fun reset() {
        GlobalScope.launch(Dispatchers.Main) {
            lightReset.turnOffAllAwait()

            viewRef.get()?.setBedroomCheckState(lightControl.getBedroomState())
            viewRef.get()?.setLibraryCheckState(lightControl.getLibraryLedState())
            viewRef.get()?.setTapeLedCheckState(false)

            viewRef.get()?.clearControls()
        }
    }
}