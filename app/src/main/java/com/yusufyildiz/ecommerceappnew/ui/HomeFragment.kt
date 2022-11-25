package com.yusufyildiz.ecommerceappnew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.adapters.CategoryRecyclerAdapter
import com.yusufyildiz.ecommerceappnew.adapters.OfferRecyclerAdapter
import com.yusufyildiz.ecommerceappnew.adapters.RecentProductRecyclerAdapter
import com.yusufyildiz.ecommerceappnew.common.ProductsData
import com.yusufyildiz.ecommerceappnew.common.showToast
import com.yusufyildiz.ecommerceappnew.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val categoryListAdapter = CategoryRecyclerAdapter(arrayListOf())
    private val offerListAdapter= OfferRecyclerAdapter(arrayListOf())
    private val recentProdcutAdapter = RecentProductRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerViewCategory()
        recyclerViewOffer()
        recyclerViewRecentProduct()

    }

    private fun recyclerViewCategory() {

        val categoryList = ProductsData.getCategoryData()

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecyclerView.adapter = categoryListAdapter
        categoryListAdapter.updateCategoryList(categoryList)

    }

    private fun recyclerViewOffer()
    {
        val offerList = ProductsData.getOfferData()
        binding.offerRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.offerRecyclerView.adapter = offerListAdapter
        offerListAdapter.updateOfferList(offerList)

    }

    private fun recyclerViewRecentProduct()
    {
        val recentProductList = ProductsData.getProductData()
        binding.recentProductRecyclerView.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.recentProductRecyclerView.adapter = recentProdcutAdapter
        recentProdcutAdapter.updateProductList(recentProductList)

    }


}