package org.android.go.sopt.presentation.gallery.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.api.ServicePool.imageService
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get()= _image

    fun setRequestBody(requestBody: ContentUriRequestBody){
        _image.value = requestBody
    }

    fun uploadProfileImage(){
        if(image.value == null){

        }else{
            imageService.uploadImage(image.value!!.toFormData()).enqueue(
                object :Callback<Unit>{
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(response.isSuccessful) {
                            Log.e("jooyy", "서버통신 성공")
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.e("fail","서버 통신 실패")
                    }
                }
            )
        }
    }
}

