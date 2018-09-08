package mech.develop.smarthouse.smarthouseclient

import android.app.Application
import mech.develop.smarthouse.smarthouseclient.di.*
import mech.develop.smarthouse.smarthouseclient.network.di.DaggerNetworkComponent
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkComponent
import mech.develop.smarthouse.smarthouseclient.network.di.NetworkModule

class SmarthouseApplication : Application(), SmarthouseApp, SmarthouseWidgetApp {

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