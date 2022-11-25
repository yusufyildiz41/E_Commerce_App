package com.yusufyildiz.ecommerceappnew.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yusufyildiz.ecommerceappnew.databinding.OfferRecyclerRowBinding
import com.yusufyildiz.ecommerceappnew.data.model.Offer

class OfferRecyclerAdapter(val offerList : ArrayList<Offer>) : RecyclerView.Adapter<OfferRecyclerAdapter.RecyclerOfferViewHolder>() {

    class RecyclerOfferViewHolder(val binding : OfferRecyclerRowBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerOfferViewHolder {

        val binding = OfferRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerOfferViewHolder(binding)


    }

    override fun onBindViewHolder(holder: RecyclerOfferViewHolder, position: Int) {

        val item = offerList[position]
        holder.binding.percentOfOffer.text = offerList[position].percentOffer.toString()

        item.offerImage?.let {
            holder.binding.offerImageView.setImageResource(it)
        }


    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    fun updateOfferList(newOfferList : List<Offer>)
    {
        offerList.clear()
        offerList.addAll(newOfferList)
        notifyDataSetChanged()
    }


}