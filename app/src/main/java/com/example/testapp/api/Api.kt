package com.example.testapp.api


import com.android.volley.error.VolleyError
import com.example.testapp.AppController
import com.example.testapp.R
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

open class Api {

    private var app: AppController = AppController.instance

    val noneAuthHeaders: Map<String, String>
        get() {
            val params = HashMap<String, String>()
            params[HEADER_CONTENT_TYPE] = HEADER_APPLICATION_JSON
            return params
        }

    val authHeaders: Map<String, String>
        get() {
            val params = HashMap<String, String>()
            params[HEADER_CONTENT_TYPE] = HEADER_APPLICATION_JSON
            return params
        }

    fun getError(error: VolleyError): String {
        error.printStackTrace()
        return if (error.networkResponse != null && error.networkResponse.data != null) {
            val responseBody = String(error.networkResponse.data, Charset.defaultCharset())
            try {
                val jsonObject = JSONObject(responseBody)
                if (jsonObject.has("message")) jsonObject.getString("message") else jsonObject.toString()
            } catch (e: JSONException) {
                app.getString(R.string.gen_error)
            }
        } else {
            app.getString(R.string.gen_error)
        }
    }

    fun getErrorCode(error: VolleyError): Int {
        error.printStackTrace()
        return if (error.networkResponse != null) error.networkResponse.statusCode else 500
    }

    companion object {
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_APPLICATION_JSON = "application/json"
    }

}
