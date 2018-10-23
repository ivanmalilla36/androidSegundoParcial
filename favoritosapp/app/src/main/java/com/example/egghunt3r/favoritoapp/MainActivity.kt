package com.example.egghunt3r.favoritoapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://favoritosweb.herokuapp.com/api/FavoritosMobile"
        favoList.layoutManager = LinearLayoutManager(this)

        fun isNetworkConnected(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        if (isNetworkConnected()){
            doAsync {
                //doAsync tuvimos que importar anko
                val result = Request(url).run()
                uiThread { longToast("com.example.egghunt3r.favoritoapp.Request performed")
                favoList.adapter = FavoritoAdapter(result, this@MainActivity)
                }
            }
            AlertDialog.Builder(this).setTitle("Connection settled down")
                .setMessage("connected to internet")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
        else{
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }
}
