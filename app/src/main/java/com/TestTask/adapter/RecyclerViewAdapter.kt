package com.TestTask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.TestTask.databinding.ItemLayoutBinding
import com.TestTask.interfaces.ItemClickListener
import com.TestTask.model.ListModel

class RecyclerViewAdapter(
    var list: MutableList<ListModel>,
    val mItemClickListener: ItemClickListener?
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(list[position]) {
                binding.image.setImageResource(image)
                binding.name.text = name
                binding.price.text = price
            }
        }

    }

    // total number of cells
    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        override fun onClick(view: View) {
            mItemClickListener?.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}