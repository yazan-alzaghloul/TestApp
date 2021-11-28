package com.example.testapp.ui.view.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.helpers.AlertDialogHelper
import com.example.testapp.helpers.DialogHelper
import com.example.testapp.ui.model.ProductModel
import com.example.testapp.ui.view.activities.CategoriesActivity
import com.example.testapp.ui.view.adapters.ProductsAdapter
import com.example.testapp.ui.viewModels.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class ProductsFragment : Fragment(), View.OnClickListener, OnDeleteBtnClick,
    AlertDialogHelper.AlertDialogInterface {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var deletedProduct: ProductModel
    private lateinit var alertForDeleting: AlertDialogHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        init(view)

        initRecyclerView()

        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        viewModel.get().observe(requireActivity(), {
            productsAdapter.setData(it);
        })

        return view
    }

    private fun init(view: View) {
        mRecyclerView = view.findViewById(R.id.recycler_view)
        floatingActionButton = view.findViewById(R.id.add_fab)
        floatingActionButton.setOnClickListener(this)

        alertForDeleting = AlertDialogHelper(
            this,
            requireContext(),
            "1",
            "Warning!",
            "Are you sure you want to delete this item?",
            "Ok",
            "Cancel"
        )
    }

    private fun initRecyclerView() {
        productsAdapter = ProductsAdapter(this)
        mRecyclerView.adapter = productsAdapter

        this.mRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        this.mRecyclerView.setHasFixedSize(true)

    }

    companion object {
        fun newInstance() = ProductsFragment()
    }

    override fun onClick(v: View?) {
        if (v == floatingActionButton) {
            viewModel.add(
                ProductModel(
                    4,
                    1,
                    "iPad pro",
                    "$1200,",
                    "https://www.etisalat.ae/en/system/wst/assets/img/2021/q3/smartphones/apple/ipad-10-2-in-space-gray-wifi-p-1b-small.jpg"
                )
            )
                .observe(requireActivity(), {
                    productsAdapter.setData(it)
                })
        }
    }

    override fun onDelete(product: ProductModel) {
        deletedProduct = product
        alertForDeleting.show()
    }

    override fun onPositiveButtonClicked() {
        viewModel.delete(deletedProduct)
            .observe(requireActivity(), {
                productsAdapter.setData(it);
            })
    }

    override fun onNegativeButtonClicked() {
        alertForDeleting.dismiss()
    }

}

interface OnDeleteBtnClick {
    fun onDelete(product: ProductModel)
}