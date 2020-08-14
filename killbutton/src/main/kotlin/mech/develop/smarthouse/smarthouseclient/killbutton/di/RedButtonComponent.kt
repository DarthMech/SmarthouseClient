package mech.develop.smarthouse.smarthouseclient.killbutton.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.coremodule.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.killbutton.widget.presenter.RedButtonPresenter
import mech.develop.smarthouse.smarthouseclient.killbutton.widget.view.RedButtonWidget
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.lightresetusecase.domain.interactor.LightResetInteractor

@ActivityScope
@Component(
        dependencies = [RepoProvider::class],
        modules = [RedButtonWidgetModule::class])
interface RedButtonComponent {
    fun inject(widget: RedButtonWidget)
}

@Module
class RedButtonWidgetModule {

    @ActivityScope
    @Provides
    fun provideRedButtonPresenter(
            lightControlKorutin: LightResetInteractor
    ): RedButtonPresenter = RedButtonPresenter(
            lightControlKorutin)

}

