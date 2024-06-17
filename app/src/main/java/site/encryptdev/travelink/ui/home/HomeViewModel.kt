package site.encryptdev.travelink.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.response.PopularResponseItem
import site.encryptdev.travelink.data.remote.retrofit.ApiConfig
import site.encryptdev.travelink.data.remote.retrofit.ApiService

class HomeViewModel : ViewModel() {

    private var _data = MutableLiveData<List<PopularResponseItem>>()
    val data : LiveData<List<PopularResponseItem>> = _data

    private var _pupuarData = MutableLiveData<List<AllPlacesResponseItem>>()
    val popularData : LiveData<List<AllPlacesResponseItem>> = _pupuarData

    private var _loading = MutableLiveData<String>("NONE")
    val loading : LiveData<String> = _loading

    fun getPopular(){
        _loading.value = "HOME"
        val client = ApiConfig.getService().getPopular()
        client.enqueue(object : Callback<List<PopularResponseItem>>{
            override fun onResponse(
                p0: Call<List<PopularResponseItem>>,
                response: Response<List<PopularResponseItem>>
            ) {
                _loading.value = "NONE"
                _data.value = response.body()
            }

            override fun onFailure(p0: Call<List<PopularResponseItem>>, p1: Throwable) {

            }

        })
    }

    fun getAllPlaces(){
        _loading.value = "POPULAR"
        val client = ApiConfig.getService().getAllPlaces()
        client.enqueue(object : Callback<List<AllPlacesResponseItem>>{
            override fun onResponse(
                p0: Call<List<AllPlacesResponseItem>>,
                response: Response<List<AllPlacesResponseItem>>
            ) {
                _loading.value = "NONE"
                _pupuarData.value = response.body()
            }

            override fun onFailure(p0: Call<List<AllPlacesResponseItem>>, p1: Throwable) {

            }

        })
    }
}