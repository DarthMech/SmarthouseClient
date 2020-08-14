package mech.develop.smarthouse.smarthouseclient.coremodule

import mech.develop.smarthouse.smarthouseclient.coremodule.di.ApplicationProvider
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RoutingProvider

interface SmarthouseApp {
    fun getAppComponent(): ApplicationProvider
    fun getRoutingComponent(): RoutingProvider
    fun getRepoComponent(): RepoProvider
}