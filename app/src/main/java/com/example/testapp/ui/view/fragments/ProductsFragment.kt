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
            viewModel.add(ProductModel(1, 1, "new Product", "$1000,", ""))
                .observe(requireActivity(), {
                    productsAdapter.setData(it)
                })
        }
    }

    override fun onDelete(product: ProductModel) {
        viewModel.delete(product)
            .observe(requireActivity(), {
                productsAdapter.setData(it);
            })
    }

    override fun onPositiveButtonClicked() {
    }

    override fun onNegativeButtonClicked() {
    }

}

interface OnDeleteBtnClick {
    fun onDelete(product: ProductModel)
}