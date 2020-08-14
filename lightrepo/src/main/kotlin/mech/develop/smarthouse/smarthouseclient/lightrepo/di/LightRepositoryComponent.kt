package mech.develop.smarthouse.smarthouseclient.lightrepo.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkComponent
import mech.develop.smarthouse.smarthouseclient.lightrepo.repo.LightRepositoryImpl
import mech.develop.smarthouse.smarthouseclient.coremodule.repo.LightRepository

@Component(
        dependencies = [NetworkComponent::class],
        modules = [LightRepositoryModule::class]
)
interface LightRepositoryComponent :
        RepoProvider

@Module
class LightRepositoryModule {

    @Provides
    fun provideRepositoryRepo(api: SmarthouseRestApi): LightRepository =
            LightRepositoryImpl(
                    api)
//            LightRepositoryFakeImpl()

}
