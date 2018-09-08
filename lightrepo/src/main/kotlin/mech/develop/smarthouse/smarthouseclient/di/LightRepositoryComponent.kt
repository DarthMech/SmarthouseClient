package mech.develop.smarthouse.smarthouseclient.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkComponent
import mech.develop.smarthouse.smarthouseclient.repo.LightRepositoryImpl
import mech.develop.smarthouse.smarthouseclient.repo.LightRepository

@Component(
        dependencies = [NetworkComponent::class],
        modules = [LightRepositoryModule::class]
)
interface LightRepositoryComponent : RepoProvider

@Module
class LightRepositoryModule {

    @Provides
    fun provideRepositoryRepo(api: SmarthouseRestApi): LightRepository =
            LightRepositoryImpl(api)

}
