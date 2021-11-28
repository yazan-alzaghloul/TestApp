package com.example.testapp.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


object RequestQueue {

    /**
     * requestQueue: RequestQueue
     */
    private lateinit var queue: RequestQueue

    /**
     * Initialize
     */
    fun init(context: Context) {
        queue = Volley.newRequestQueue(context)
    }

    fun addToQueue(request: Request<*>) {
        request.setShouldCache(false)
        queue.add<Any>(request as Request<Any>?)
    }

    fun clear() {
        Log.d("Logout", "clear RequestQueue")
        queue.cache.clear()
    }

}