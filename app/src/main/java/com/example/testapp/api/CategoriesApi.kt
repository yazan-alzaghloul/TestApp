package com.example.testapp.api


import android.util.Log
import com.example.testapp.utils.URLs
import com.android.volley.Request
import com.android.volley.request.JsonArrayRequest
import com.android.volley.request.JsonObjectRequest
import com.example.testapp.ui.model.Category
import com.google.gson.Gson
import org.json.JSONArray

class CategoriesApi : Api() {

    fun getCategories(listener: CategoriesListener) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            URLs.Categories,
            null,
            {
                Log.d("Categories", it.toString())
                val categories: ArrayList<Category> = ArrayList()
                for (i in 0 until it.length()) {
                    val cat: Category = Category(
                        it.optJSONObject(i).optLong("id"),
                        it.optJSONObject(i).optString("name"),
                        it.optJSONObject(i).optString("image"),
                    )
                    categories.add(cat)
                }
                listener.categoriesSuccess(
                    categories
                )
            },
            { error ->
                listener.categoriesFailed(getError(error), getErrorCode(error))
            }
        )
        RequestQueue.addToQueue(request)
    }

    interface CategoriesListener {
        fun categoriesSuccess(categories: ArrayList<Category>)
        fun categoriesFailed(message: String, code: Int)
    }
}