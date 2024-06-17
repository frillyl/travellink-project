package site.encryptdev.travelink.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.response.PopularResponseItem
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem

interface ApiService {

    @GET("places/popular")
    fun getPopular(): Call<List<PopularResponseItem>>

    @GET("places")
    fun getAllPlaces(): Call<List<AllPlacesResponseItem>>

    @GET("categories")
    fun getAllCategories(): Call<List<String>>

    @GET("places/{id}/recommendations")
    fun getRecomendation(@Path("id") id: String): Call<List<RecomendationResponseItem>>

}