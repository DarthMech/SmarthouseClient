package mech.develop.smarthouse.smarthouseclient.mainscreen.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.coremodule.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RoutingProvider
import mech.develop.smarthouse.smarthouseclient.lightcontrolusecase.domain.interactor.LightControlInteractor
import mech.develop.smarthouse.smarthouseclient.lightresetusecase.domain.interactor.LightResetInteractor
import mech.develop.smarthouse.smarthouseclient.mainscreen.presentation.activity.SmarthouseActivity
import mech.develop.smarthouse.smarthouseclient.mainscreen.presentation.presenters.SmarthousePresenter

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

