package mech.develop.smarthouse.smarthouseclient

import mech.develop.smarthouse.smarthouseclient.di.ApplicationProvider
import mech.develop.smarthouse.smarthouseclient.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.di.RoutingProvider

interface SmarthouseApp {
    fun getAppComponent(): ApplicationProvider
    fun getRoutingComponent(): RoutingProvider
    fun getRepoComponent(): RepoProvider
}