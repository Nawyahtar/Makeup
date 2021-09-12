package com.example.makeup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LRecyclerAdapter (private val clickListener: LRecyclerAdapter.onDetailItemClickListener):androidx.recyclerview.widget.ListAdapter<LData,LRecyclerAdapter.DataViewHolder>(
    object :DiffUtil.ItemCallback<LData>(){
        override fun areItemsTheSame(oldItem: LData, newItem: LData): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: LData, newItem: LData): Boolean {
            return oldItem==newItem
        }

    }
){
    class DataViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val ivL=itemView.findViewById<ImageView>(R.id.ivImage)
        val tvName=itemView.findViewById<TextView>(R.id.tvName)

        fun initialize(item: LData, action: LRecyclerAdapter.onDetailItemClickListener){
            Glide.with(itemView.context).load(item.image).into(ivL)
            tvName.text=item.name

            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.loreal_list,parent,false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.initialize(getItem(position),clickListener)
    }
    interface onDetailItemClickListener{
        fun onItemClick(item:LData,position: Int)
    }
}