package mech.develop.smarthouse.smarthouseclient.killbutton.widget.presenter

import mech.develop.smarthouse.smarthouseclient.lightresetusecase.domain.interactor.LightResetInteractor

class RedButtonPresenter(private val interactor: LightResetInteractor) {

    fun onKillAction() {
        interactor.turnOffAll()
    }

}