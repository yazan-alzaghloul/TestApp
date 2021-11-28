package com.example.testapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.api.ProductsApi
import com.example.testapp.helpers.ToastHelper
import com.example.testapp.ui.model.ProductModel

class ProductsViewModel : ViewModel(), ProductsApi.ProductsListener {

    private val requests: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()

    fun get(): MutableLiveData<ArrayList<ProductModel>> {
        ProductsApi().getProducts(this)
        return requests
    }

    fun add(newProduct: ProductModel): MutableLiveData<ArrayList<ProductModel>> {
        requests.value!!.add(0, newProduct);
        return requests
    }

    fun delete(newProduct: ProductModel): MutableLiveData<ArrayList<ProductModel>> {
        requests.value!!.remove(newProduct);
        return requests
    }

    override fun productsSuccess(products: ArrayList<ProductModel>) {
        requests.postValue(products)
    }

    override fun productsFailed(message: String, code: Int) {
        ToastHelper().long(message)
    }
}