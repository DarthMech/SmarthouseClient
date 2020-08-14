package mech.develop.smarthouse.smarthouseclient.coremodule.di

import mech.develop.smarthouse.smarthouseclient.coremodule.navigation.AboutScreenRouter

interface RoutingProvider {
    fun provideAboutScreenRouter(): AboutScreenRouter
}