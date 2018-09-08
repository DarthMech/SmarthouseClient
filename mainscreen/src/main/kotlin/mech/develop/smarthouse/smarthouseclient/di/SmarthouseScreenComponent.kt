package mech.develop.smarthouse.smarthouseclient.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightControlInteractor
import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightResetInteractor
import mech.develop.smarthouse.smarthouseclient.presentation.activity.SmarthouseActivity
import mech.develop.smarthouse.smarthouseclient.presentation.presenters.SmarthousePresenter

@ActivityScope
@Component(
        dependencies = [RepoProvider::class, RoutingProvider::class],
        modules = [SmarthouseScreenModule::class])
interface SmarthouseScreenComponent {
    fun inject(fragment: SmarthouseActivity)
}

@Module
class SmarthouseScreenModule {

    @ActivityScope
    @Provides
    fun provideSmarthousePresenter(
            lightControlKorutin: LightControlInteractor,
            lightResetInteractor: LightResetInteractor
    ): SmarthousePresenter = SmarthousePresenter(lightControlKorutin, lightResetInteractor)

}

