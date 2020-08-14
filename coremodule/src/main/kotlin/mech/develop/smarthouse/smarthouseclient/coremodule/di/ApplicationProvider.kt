package mech.develop.smarthouse.smarthouseclient.coremodule.di

import android.content.Context

interface ApplicationProvider {
    fun provideContext(): Context
}