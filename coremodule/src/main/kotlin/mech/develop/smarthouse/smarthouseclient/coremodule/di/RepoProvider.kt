package mech.develop.smarthouse.smarthouseclient.coremodule.di

import mech.develop.smarthouse.smarthouseclient.coremodule.repo.LightRepository

interface RepoProvider {
    fun provideLightControlRepo(): LightRepository
}