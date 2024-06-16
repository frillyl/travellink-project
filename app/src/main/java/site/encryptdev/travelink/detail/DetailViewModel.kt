package site.encryptdev.travelink.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.encryptdev.travelink.retrofit.ApiClient

class DetailViewModel : ViewModel() {
    private val _detail = MutableLiveData<DetailResponse?>()
    val detail: LiveData<DetailResponse?> get() = _detail

    fun fetchDetail(id: Int) {
        ApiClient.apiService.getDetail(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    _detail.value = response.body()
                } else {
                    _detail.value = null
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _detail.value = null
            }
        })
    }
}