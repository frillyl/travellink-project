package site.encryptdev.travelink.data.remote.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getService(): ApiService{

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retorfit= Retrofit.Builder()
            .baseUrl("http://34.101.117.174:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retorfit.create(ApiService::class.java)
    }
}