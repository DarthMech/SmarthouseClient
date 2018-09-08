package mech.develop.smarthouse.smarthouseclient.di

import mech.develop.smarthouse.smarthouseclient.navigation.AboutScreenRouter

interface RoutingProvider {
    fun provideAboutScreenRouter(): AboutScreenRouter
}