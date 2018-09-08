package mech.develop.smarthouse.smarthouseclient.di

import android.content.Context

interface ApplicationProvider {
    fun provideContext(): Context
}