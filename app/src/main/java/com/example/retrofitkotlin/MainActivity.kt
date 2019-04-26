package com.example.retrofitkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val service: HttpBinServiceString = retrofit.create(HttpBinServiceString::class.java)

        val call = service.getUserAgent()

        call.enqueue(object: Callback<String>{

            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                Log.i(TAG, "User agent response: ${response?.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        // JSON

        val retrofitJson = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serviceJson: HttpBinServiceJson = retrofitJson.create(HttpBinServiceJson::class.java)

        val callJson = serviceJson.getUserInfo()
        callJson.enqueue(object: Callback<GetData> {

            override fun onResponse(call: Call<GetData>?, response: Response<GetData>?) {

                val getData = response?.body()
                Log.i(TAG, "Receive url: ${getData?.url}")
            }

            override fun onFailure(call: Call<GetData>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
