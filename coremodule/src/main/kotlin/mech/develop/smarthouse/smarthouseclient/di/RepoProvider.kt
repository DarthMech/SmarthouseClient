package mech.develop.smarthouse.smarthouseclient.di

import mech.develop.smarthouse.smarthouseclient.repo.LightRepository

interface RepoProvider {
    fun provideLightControlRepo(): LightRepository
}