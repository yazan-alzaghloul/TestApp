package com.example.testapp.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.ui.model.ProductModel
import com.example.testapp.ui.view.fragments.OnDeleteBtnClick

class ProductsAdapter(
    private var listener: OnDeleteBtnClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productsListModel: ArrayList<ProductModel> = ArrayList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_product, parent, false)
        context = parent.context

        return CategoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryHolder).setData(productsListModel[position])
    }

    override fun getItemCount(): Int {
        return if (productsListModel.isNullOrEmpty())
            0
        else productsListModel.size
    }

    fun setData(it: ArrayList<ProductModel>?) {
        if (it != null) {
            this.productsListModel = it
            notifyDataSetChanged()
        }
    }


    private inner class CategoryHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgProduct: ImageView = itemView.findViewById(R.id.image_product)
        var tvNameProduct: TextView = itemView.findViewById(R.id.name_product)
        var tvPriceProduct: TextView = itemView.findViewById(R.id.price_product)
        var tvDelete: TextView = itemView.findViewById(R.id.tv_delete)

        fun setData(productModel: ProductModel) {
            tvNameProduct.text = productModel.name
            tvPriceProduct.text = productModel.price
            Glide.with(context).load(productModel.image).placeholder(R.mipmap.ic_launcher)
                .into(imgProduct)
            tvDelete.setOnClickListener {
                listener.onDelete(productModel);
            }
        }

    }
}