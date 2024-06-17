package site.encryptdev.travelink.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class DetailViewModel : ViewModel() {
    private val _detail = MutableLiveData<DetailResponse?>()
    val detail: LiveData<DetailResponse?> get() = _detail


}