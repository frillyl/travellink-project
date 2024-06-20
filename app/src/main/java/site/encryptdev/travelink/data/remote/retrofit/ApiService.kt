package site.encryptdev.travelink.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.response.PopularResponseItem
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem

interface ApiService {

    @GET("places/popular")
    fun getPopular(): Call<List<PopularResponseItem>>

    @GET("places")
    fun getAllPlaces(@Query("category") category: String? = null): Call<List<AllPlacesResponseItem>>

    @GET("categories")
    fun getAllCategories(): Call<List<String>>

    @GET("places/{id}/recommendations")
    fun getRecomendation(@Path("id") id: String): Call<List<RecomendationResponseItem>>

}