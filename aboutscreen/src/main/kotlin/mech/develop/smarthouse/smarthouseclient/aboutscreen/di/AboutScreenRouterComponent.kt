package mech.develop.smarthouse.smarthouseclient.aboutscreen.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.aboutscreen.navigation.AboutScreenRouterImpl
import mech.develop.smarthouse.smarthouseclient.coremodule.navigation.AboutScreenRouter
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RoutingProvider

@Component(modules = [AboutScreenRouterModule::class])
interface AboutScreenRouterComponent :
        RoutingProvider

@Module
class AboutScreenRouterModule {
    @Provides
    fun provideAboutScreenRouter(): AboutScreenRouter = AboutScreenRouterImpl()
}
