package com.example.makeup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MaybellintViewModel : ViewModel() {
    val dataListObservable = MutableLiveData<List<Data>>()
    val dataList = mutableListOf<Data>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://makeup-api.herokuapp.com/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()

        val dataCallBack = retrofit.create(DataCallBack::class.java)
        dataCallBack.getBrandName(
            name = "maybelline"
        ).enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    response.body()?.let { list: List<Data> ->
                        dataList.clear()
                        dataList.addAll(list)
                        dataListObservable.postValue(dataList)
                    }
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                t.printStackTrace()


            }

        })
    }
}