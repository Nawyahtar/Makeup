package com.example.makeup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LViewModel : ViewModel() {
    val dataListObservable = MutableLiveData<List<LData>>()
    val dataList = mutableListOf<LData>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://makeup-api.herokuapp.com/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()

        val lDataCallBack=retrofit.create(LDataCallBack::class.java)
        lDataCallBack.getBrandName(
            name = "L'oreal"
        ).enqueue(object :Callback<List<LData>>{
            override fun onResponse(call: Call<List<LData>>, response: Response<List<LData>>) {
                if (response.isSuccessful) {
                    response.body()?.let { list: List<LData> ->
                        dataList.clear()
                        dataList.addAll(list)
                        dataListObservable.postValue(dataList)
                    }
                }
            }

            override fun onFailure(call: Call<List<LData>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}