package com.example.volleypractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    //https://habr.com/ru/post/495976/
    private val url = "https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22"
    private var mRequestQueue: RequestQueue? = null
    private var temp = 0.0
    private var windSpeed: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRequestQueue = Volley.newRequestQueue(this)

        getWeather()
    }

    private fun getWeather() {
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val weather = response.getJSONObject("main")
                val wind = response.getJSONObject("wind")
                temp = weather.getDouble("temp")
                windSpeed = wind.getDouble("speed")
                setValues()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) { error ->
            Log.e("mLog", "Error: $error")
        }
        mRequestQueue!!.add(request)
    }

    private fun setValues() {
        Log.i("mLog", "Temperature: $temp")
        Log.i("mLog", "Wind speed: $windSpeed")
    }

}