package com.yusufyildiz.ecommerceappnew.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yusufyildiz.ecommerceappnew.databinding.CategoryRecyclerRowBinding
import com.yusufyildiz.ecommerceappnew.data.model.Category

class CategoryRecyclerAdapter(val categoryList : ArrayList<Category>) : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(val binding : CategoryRecyclerRowBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val item = categoryList[position]
        holder.binding.categoryNameText.text = item.categoryText.toString()

        categoryList[position].categoryImage?.let {
            holder.binding.categoryImage.setImageResource(it)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateCategoryList(newCategoryList : List<Category>)
    {
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }


}