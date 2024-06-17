package site.encryptdev.travelink.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.response.PopularResponseItem

interface ApiService {

    @GET("places/popular")
    fun getPopular(): Call<List<PopularResponseItem>>

    @GET("places")
    fun getAllPlaces(): Call<List<AllPlacesResponseItem>>

}