package com.example.testapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.api.CategoriesApi
import com.example.testapp.helpers.ToastHelper
import com.example.testapp.ui.model.Category

class CategoriesViewModel : ViewModel(), CategoriesApi.CategoriesListener {

    private val requests: MutableLiveData<ArrayList<Category>> = MutableLiveData()

    fun get(): MutableLiveData<ArrayList<Category>> {
        CategoriesApi().getCategories(this)
        return requests
    }

    override fun categoriesSuccess(categories: ArrayList<Category>) {
        requests.postValue(categories)
    }

    override fun categoriesFailed(message: String, code: Int) {
        ToastHelper().long(message)
    }
}