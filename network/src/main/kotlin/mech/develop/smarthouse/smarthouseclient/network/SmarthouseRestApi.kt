package mech.develop.smarthouse.smarthouseclient.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SmarthouseRestApi {

    // test
//    @GET("light/")
//    fun getInt(): Single<Int>

//    @GET("light/q")
//    fun getString(): Single<String>

    // light
    @GET("light/bedroomState")
    fun getBedroomLedState(): Call<Boolean>

    @GET("light/libraryState")
    fun getLibraryLedState(): Call<Boolean>

    @Headers("Content-Type: application/json")
    @POST("light/setBedroomLedState")
    fun setBedroomState(@Body state: Boolean): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("light/setLibraryLedState")
    fun setLibraryLedState(@Body state: Boolean): Call<Void>

    // led strip
    @GET("light/ledStripLight/green")
    fun getGreenLedStripState(): Call<Int>

//    @Headers("Content-Type: application/json")
//    @POST("light/ledStripLight/red")
//    fun setRedLedStripState(@Body state: Int): Single<Response<Void>>
//
//    @Headers("Content-Type: application/json")
//    @POST("light/ledStripLight/green")
//    fun setGreenLedStripState(@Body state: Int): Single<Response<Void>>
//
//    @Headers("Content-Type: application/json")
//    @POST("light/ledStripLight/blue")
//    fun setBlueLedStripState(@Body state: Int): Single<Response<Void>>

    // control
    @POST("light/turnOffAll")
    fun turnOffAll(): Call<Void>
}