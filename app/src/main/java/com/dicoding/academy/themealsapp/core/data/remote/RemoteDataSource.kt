package com.dicoding.academy.themealsapp.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academy.themealsapp.core.data.remote.network.ApiService
import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoriesResponse
import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getCategories(): LiveData<List<CategoryResponse>> {
        val resultData = MutableLiveData<List<CategoryResponse>>()

        //get data from remote api
        val client = apiService.getCategories()

        client.enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                val dataArray = response.body()
                if (dataArray != null) {
                    resultData.value =  dataArray.categories
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}