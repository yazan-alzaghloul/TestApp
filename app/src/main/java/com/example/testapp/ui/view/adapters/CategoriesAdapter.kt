package com.example.testapp.ui.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.ui.model.Category
import com.example.testapp.ui.view.activities.ProductsActivity

class CategoriesAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoriesListModel: ArrayList<Category> = ArrayList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_category, parent, false)
        context = parent.context

        return CategoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryHolder).setData(categoriesListModel[position])
    }

    override fun getItemCount(): Int {
        return if (categoriesListModel.isNullOrEmpty())
            0
        else categoriesListModel.size
    }

    fun setData(it: ArrayList<Category>?) {
        if (it != null) {
            this.categoriesListModel = it
            notifyDataSetChanged()
        }
    }


    private inner class CategoryHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var clCategoriesRoot: ConstraintLayout = itemView.findViewById(R.id.category_root)
        var imgCat: ImageView = itemView.findViewById(R.id.image_category)
        var tvNameCat: TextView = itemView.findViewById(R.id.name_category)

        init {
            clCategoriesRoot.setOnClickListener {
                val intent = Intent(context, ProductsActivity::class.java)
                context.startActivity(intent)
            }
        }

        fun setData(categoryModel: Category) {
            tvNameCat.text = categoryModel.name
            Glide.with(context).load(categoryModel.image).placeholder(R.mipmap.ic_launcher)
                .into(imgCat)
        }
    }


}