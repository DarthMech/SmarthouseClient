package mech.develop.smarthouse.smarthouseclient.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.navigation.AboutScreenRouter
import mech.develop.smarthouse.smarthouseclient.navigation.AboutScreenRouterImpl

@Component(modules = [AboutScreenRouterModule::class])
interface AboutScreenRouterComponent : RoutingProvider

@Module
class AboutScreenRouterModule {
    @Provides
    fun provideAboutScreenRouter(): AboutScreenRouter = AboutScreenRouterImpl()
}
