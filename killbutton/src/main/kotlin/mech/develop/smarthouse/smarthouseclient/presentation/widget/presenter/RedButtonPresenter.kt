package mech.develop.smarthouse.smarthouseclient.presentation.widget.presenter

import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightResetInteractor

class RedButtonPresenter(private val interactor: LightResetInteractor) {

    fun onKillAction() {
        interactor.turnOffAll()
    }

}