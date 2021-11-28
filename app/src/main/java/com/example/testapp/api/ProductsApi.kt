package com.example.testapp.api


import com.android.volley.Request
import com.android.volley.request.JsonArrayRequest
import com.example.testapp.ui.model.ProductModel
import com.example.testapp.utils.URLs

class ProductsApi : Api() {

    fun getProducts(listener: ProductsListener) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            URLs.Products,
            null,
            {
                val products: ArrayList<ProductModel> = ArrayList()
                for (i in 0 until it.length()) {
                    val product = ProductModel(
                        it.optJSONObject(i).optLong("id"),
                        it.optJSONObject(i).optLong("catId"),
                        it.optJSONObject(i).optString("name"),
                        it.optJSONObject(i).optString("price"),
                        it.optJSONObject(i).optString("image"),
                    )
                    products.add(product)
                }
                listener.productsSuccess(
                    products
                )
            },
            { error ->
                listener.productsFailed(getError(error), getErrorCode(error))
            }
        )
        RequestQueue.addToQueue(request)
    }

    interface ProductsListener {
        fun productsSuccess(products: ArrayList<ProductModel>)
        fun productsFailed(message: String, code: Int)
    }
}