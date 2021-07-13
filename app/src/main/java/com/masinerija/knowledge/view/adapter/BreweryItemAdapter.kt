package com.masinerija.knowledge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masinerija.knowledge.R
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.databinding.ListItemBreweryBinding

typealias BreweryClickListener = (Brewery) -> Unit

class BreweryItemAdapter(var clickListener: BreweryClickListener): ListAdapter<Brewery, BreweryItemAdapter.ViewHolder>(BreweryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.binding.btnSave.setOnClickListener{
            clickListener(getItem(position))
        }
    }

    class ViewHolder(val binding: ListItemBreweryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Brewery){
            binding.txtBrewery.text = binding.root.context.getString(R.string.brewery_info, item.name, item.city)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBreweryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BreweryDiffCallback : DiffUtil.ItemCallback<Brewery>() {
    override fun areItemsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem == newItem
    }

}
