package com.example.testapp

import android.app.Application
import android.content.Context
import com.example.testapp.api.RequestQueue

class AppController : Application() {


    init {
        app = this
    }

    companion object {
        private var app: AppController? = null
        val instance get() = app!!
        val context: Context get() = app!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        // initialize the request queue
        RequestQueue.init(applicationContext)
    }

}