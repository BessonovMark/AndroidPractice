package com.aleshchenkov.mylearningproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhonesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mPhonesList: ArrayList<PhoneModel> = ArrayList()
    fun setupPhones(phonesList: ArrayList<PhoneModel>){
        mPhonesList.clear()
        mPhonesList.addAll(phonesList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return PhonesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PhonesViewHolder){
            holder.bind(mPhones = mPhonesList[position])
        }
    }

    override fun getItemCount(): Int {
        return mPhonesList.count()
    }
    class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(mPhones: PhoneModel){
            itemView.rootView.findViewById<TextView>(R.id.phone_name).text = mPhones.name
            itemView.rootView.findViewById<TextView>(R.id.phone_price).text = mPhones.price
            itemView.rootView.findViewById<TextView>(R.id.phone_date).text = mPhones.date
            itemView.rootView.findViewById<TextView>(R.id.phone_camera_score).text = mPhones.score
        }
    }
}