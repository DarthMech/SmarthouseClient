package mech.develop.smarthouse.smarthouseclient.presentation.presenters

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
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
        launch(UI) {
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

    // led
    fun setBlueLedStripState(progress: Int) {
    }

    fun setGreenLedStripState(progress: Int) {
    }

    fun setRedLedStripState(progress: Int) {
    }

    // reset
    fun killAll() {
        reset()
    }

    // ------------------------------ Inner methods -----------------------------------------------
    private fun reset() {
        launch(UI) {
            lightReset.turnOffAllAwait()

            viewRef.get()?.setBedroomCheckState(lightControl.getBedroomState())
            viewRef.get()?.setLibraryCheckState(lightControl.getLibraryLedState())
        }
    }
}