package mech.develop.smarthouse.smarthouseclient.network.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "http://localhost"

@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun provideSmarthouseRestApi(): SmarthouseRestApi
}

@Module
class NetworkModule {

    @Provides
    fun createApi(): SmarthouseRestApi {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        return retrofit.create(SmarthouseRestApi::class.java)
    }
}