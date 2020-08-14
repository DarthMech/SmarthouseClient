package mech.develop.smarthouse.smarthouseclient.killbutton

import mech.develop.smarthouse.smarthouseclient.killbutton.di.RedButtonComponent

interface SmarthouseWidgetApp {
    fun getWidgetComponent() : RedButtonComponent
}