package site.encryptdev.travelink.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.retrofit.ApiConfig

class CategoryViewModel: ViewModel() {

    private var _data = MutableLiveData<List<AllPlacesResponseItem>>()
    val data : LiveData<List<AllPlacesResponseItem>> = _data

    fun getData(category : String){
        val client = ApiConfig.getService().getAllPlaces(category)
        client.enqueue(object : Callback<List<AllPlacesResponseItem>>{
            override fun onResponse(
                p0: Call<List<AllPlacesResponseItem>>,
                response: Response<List<AllPlacesResponseItem>>
            ) {
                _data.value = response.body()
            }

            override fun onFailure(p0: Call<List<AllPlacesResponseItem>>, p1: Throwable) {

            }

        })
    }
}