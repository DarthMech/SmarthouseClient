package mech.develop.smarthouse.smarthouseclient.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.di.scope.ActivityScope
import mech.develop.smarthouse.smarthouseclient.domain.interactor.LightResetInteractor
import mech.develop.smarthouse.smarthouseclient.presentation.widget.presenter.RedButtonPresenter
import mech.develop.smarthouse.smarthouseclient.presentation.widget.view.RedButtonWidget

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
    ): RedButtonPresenter = RedButtonPresenter(lightControlKorutin)

}

