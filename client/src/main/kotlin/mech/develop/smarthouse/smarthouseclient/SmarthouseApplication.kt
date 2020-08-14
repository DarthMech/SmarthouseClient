package mech.develop.smarthouse.smarthouseclient

import android.app.Application
import mech.develop.smarthouse.smarthouseclient.aboutscreen.di.AboutScreenRouterComponent
import mech.develop.smarthouse.smarthouseclient.aboutscreen.di.AboutScreenRouterModule
import mech.develop.smarthouse.smarthouseclient.aboutscreen.di.DaggerAboutScreenRouterComponent
import mech.develop.smarthouse.smarthouseclient.coremodule.aboutscreen.di.*
import mech.develop.smarthouse.smarthouseclient.coremodule.SmarthouseApp
import mech.develop.smarthouse.smarthouseclient.coremodule.di.ApplicationProvider
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RepoProvider
import mech.develop.smarthouse.smarthouseclient.coremodule.di.RoutingProvider
import mech.develop.smarthouse.smarthouseclient.killbutton.SmarthouseWidgetApp
import mech.develop.smarthouse.smarthouseclient.killbutton.di.DaggerRedButtonComponent
import mech.develop.smarthouse.smarthouseclient.killbutton.di.RedButtonComponent
import mech.develop.smarthouse.smarthouseclient.killbutton.di.RedButtonWidgetModule
import mech.develop.smarthouse.smarthouseclient.lightrepo.di.DaggerLightRepositoryComponent
import mech.develop.smarthouse.smarthouseclient.lightrepo.di.LightRepositoryComponent
import mech.develop.smarthouse.smarthouseclient.lightrepo.di.LightRepositoryModule
import mech.develop.smarthouse.smarthouseclient.network.di.DaggerNetworkComponent
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkComponent
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkModule

class SmarthouseApplication : Application(),
                              SmarthouseApp,
                              SmarthouseWidgetApp {

    private val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }


    private val routingComponent: AboutScreenRouterComponent by lazy {
        DaggerAboutScreenRouterComponent
                .builder()
                .aboutScreenRouterModule(AboutScreenRouterModule())
                .build()
    }

    private val repoComponent: LightRepositoryComponent by lazy {
        val netComponent: NetworkComponent =
                DaggerNetworkComponent
                        .builder()
                        .networkModule(NetworkModule())
                        .build()

        DaggerLightRepositoryComponent
                .builder()
                .networkComponent(netComponent)
                .lightRepositoryModule(LightRepositoryModule())
                .build()
    }

    private val redButtonComponent: RedButtonComponent by lazy {

        DaggerRedButtonComponent.builder()
                .repoProvider(repoComponent)
                .redButtonWidgetModule(RedButtonWidgetModule())
                .build()
    }

    // --- SmarthouseApp ---
    override fun getAppComponent(): ApplicationProvider = component
    override fun getRoutingComponent(): RoutingProvider = routingComponent
    override fun getRepoComponent(): RepoProvider = repoComponent

    // --- SmarthouseWidgetApp ---
    override fun getWidgetComponent(): RedButtonComponent = redButtonComponent

}