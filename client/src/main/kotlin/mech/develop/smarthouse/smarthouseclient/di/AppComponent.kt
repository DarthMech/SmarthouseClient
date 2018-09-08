package mech.develop.smarthouse.smarthouseclient.di

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent : ApplicationProvider

@Module
class AppModule(private val applicationContext: Context) {
    @Provides
    internal fun provaideAppContext(): Context = applicationContext
}