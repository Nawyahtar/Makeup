package com.example.makeup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MaybellineRecyclerAdapter(private val clickListener: onDetailItemClickListener) :androidx.recyclerview.widget.ListAdapter<Data,MaybellineRecyclerAdapter.DataViewHolder>(
    object :DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
           return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem==newItem
        }

    }
){
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGlossier=itemView.findViewById<ImageView>(R.id.ivGlossier)
        val tvName=itemView.findViewById<TextView>(R.id.tvName)


        @SuppressLint("CheckResult")
        fun initialize(item: Data, action:onDetailItemClickListener){
           Glide.with(itemView.context).load(item.image).into(ivGlossier)
            tvName.text=item.name

            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.maybelline_list,parent,false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.initialize(getItem(position),clickListener)
    }

    interface onDetailItemClickListener{
        fun onItemClick(item:Data,position: Int)
    }
}