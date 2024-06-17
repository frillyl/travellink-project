package site.encryptdev.travelink.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem
import site.encryptdev.travelink.data.remote.retrofit.ApiConfig


class DetailViewModel : ViewModel() {
    private val _recomendation = MutableLiveData<List<RecomendationResponseItem>>()
    val recomendation : LiveData<List<RecomendationResponseItem>> = _recomendation

    fun getRecomendation(id: String){
        val client = ApiConfig.getService().getRecomendation(id)
        client.enqueue(object : Callback<List<RecomendationResponseItem>>{
            override fun onResponse(
                p0: Call<List<RecomendationResponseItem>>,
                response: Response<List<RecomendationResponseItem>>
            ) {
                _recomendation.value = response.body()
            }

            override fun onFailure(p0: Call<List<RecomendationResponseItem>>, p1: Throwable) {

            }

        })
    }


}