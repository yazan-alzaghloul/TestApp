package com.example.testapp.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.ui.view.adapters.CategoriesAdapter
import com.example.testapp.ui.viewModels.CategoriesViewModel


class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view)

        categoriesAdapter = CategoriesAdapter()
        mRecyclerView.adapter = categoriesAdapter

        this.mRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        this.mRecyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewModel.get().observe(requireActivity(), {
            Log.d("API_Response", it.toString())
            categoriesAdapter.setData(it);
        })

        return view
    }

    companion object {
        fun newInstance() = CategoriesFragment()
    }

}