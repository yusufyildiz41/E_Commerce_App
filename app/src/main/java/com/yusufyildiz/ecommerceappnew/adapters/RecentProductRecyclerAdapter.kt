package com.yusufyildiz.ecommerceappnew.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yusufyildiz.ecommerceappnew.databinding.RecentProductRowBinding
import com.yusufyildiz.ecommerceappnew.data.model.Product

class RecentProductRecyclerAdapter(val productList: ArrayList<Product>) :
    RecyclerView.Adapter<RecentProductRecyclerAdapter.RecentProductViewHolder>() {

    class RecentProductViewHolder(val binding: RecentProductRowBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentProductViewHolder {

        val binding =
            RecentProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentProductViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecentProductViewHolder, position: Int) {

        var item = productList[position]
        item.productImage?.let {
            holder.binding.recentProductImageView.setImageResource(
                it
            )
        }

        holder.binding.recentProductNameText.text = item.productName.toString()
        holder.binding.recentProductPriceText.text = item.productPrice.toString()


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()

    }

}