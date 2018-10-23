package com.example.egghunt3r.favoritoapp

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class Request(private val url: String) {

    fun run(): FavoritoResult {
        val repoListJsonStr = URL(url).readText()
        Log.d("respuesta", repoListJsonStr)
        return Gson().fromJson(repoListJsonStr, FavoritoResult::class.java)
    }
}