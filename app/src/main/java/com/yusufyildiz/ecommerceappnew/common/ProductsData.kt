package com.yusufyildiz.ecommerceappnew.common

import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.data.model.Category
import com.yusufyildiz.ecommerceappnew.data.model.Offer
import com.yusufyildiz.ecommerceappnew.data.model.Product

object ProductsData {

    fun getCategoryData() : List<Category>
    {
        return listOf(
            Category(R.drawable.apparel,"Apparel"),
            Category(R.drawable.school,"School"),
            Category(R.drawable.sports,"Sports"),
            Category(R.drawable.electronic,"Electronic"),
            Category(R.drawable.all,"All")
        )
    }

    fun getOfferData() : List<Offer>
    {
        return listOf(
            Offer("School Collections",60,R.drawable.school_collections_image),
            Offer("Electronic Collections",40,R.drawable.school_collections_image)
        )
    }

    fun getProductData() : List<Product>
    {
        return listOf(
            Product("Monitor LG 22''inc 4k ",210.99,R.drawable.school_collections_image),
            Product("Playstation 4 - With Games",500.00,R.drawable.electronic_offer),
            Product("XBOX - With Games",500.00,R.drawable.school_collections_image),
            Product("HP Laptop CPU i5 8 gb RAM",500.00,R.drawable.electronic_offer),
            Product("Asus Laptop CPU i5 8 gb RAM",500.00,R.drawable.school_collections_image),
            Product("Monster Laptop CPU i5 16 gb RAM",500.00,R.drawable.electronic_offer),
        )
    }




}