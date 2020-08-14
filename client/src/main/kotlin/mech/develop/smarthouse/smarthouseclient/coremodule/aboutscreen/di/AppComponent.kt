package mech.develop.smarthouse.smarthouseclient.coremodule.aboutscreen.di

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.coremodule.di.ApplicationProvider

@Component(modules = [AppModule::class])
interface AppComponent : ApplicationProvider

@Module
class AppModule(private val applicationContext: Context) {
    @Provides
    internal fun provaideAppContext(): Context = applicationContext
}