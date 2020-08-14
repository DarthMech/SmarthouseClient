package mech.develop.smarthouse.smarthouseclient.network.di

import dagger.Component
import dagger.Module
import dagger.Provides
import mech.develop.smarthouse.smarthouseclient.network.BuildConfig
import mech.develop.smarthouse.smarthouseclient.network.SmarthouseRestApi
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun provideSmarthouseRestApi(): SmarthouseRestApi
}

@Module
class NetworkModule {

    private val baseUrl: HttpUrl = BuildConfig.API_URL.toHttpUrl()

    @Provides
    fun createApi(): SmarthouseRestApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        return retrofit.create(SmarthouseRestApi::class.java)
    }
}