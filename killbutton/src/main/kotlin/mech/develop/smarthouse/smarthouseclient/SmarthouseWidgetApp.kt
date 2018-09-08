package mech.develop.smarthouse.smarthouseclient

import mech.develop.smarthouse.smarthouseclient.di.RedButtonComponent

interface SmarthouseWidgetApp {
    fun getWidgetComponent() : RedButtonComponent
}