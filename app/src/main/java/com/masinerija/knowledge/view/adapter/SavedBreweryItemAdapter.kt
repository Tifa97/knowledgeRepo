package com.masinerija.knowledge.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masinerija.knowledge.R
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.databinding.ListItemBreweryBinding
import com.masinerija.knowledge.databinding.ListItemSavedBreweryBinding

typealias SavedBreweryClickListener = (Brewery) -> Unit

class SavedBreweryItemAdapter(var clickListener: SavedBreweryClickListener):
    RecyclerView.Adapter<SavedBreweryItemAdapter.ViewHolder>(){

    private val items = ArrayList<Brewery>()

    fun setData(breweries: List<Brewery>) {
        val diffCallback = SavedBreweryDiffCallback(items, breweries)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(breweries)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = items[position])
        holder.itemView.tag = position

        holder.binding.btnEdit.setOnClickListener{
            clickListener(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(val binding: ListItemSavedBreweryBinding): RecyclerView.ViewHolder(binding.root) {
        val context: Context = binding.root.context

        fun bind(item: Brewery){
            binding.txtBrewery.text = context.getString(R.string.brewery_info, item.name, item.city)
            binding.txtStreet.text = context.getString(R.string.street, handleIfNull(item.street))
            binding.txtPhone.text = context.getString(R.string.phone, handleIfNull(item.phone))
            binding.txtUrl.text = context.getString(R.string.website, handleIfNull(item.websiteUrl))
        }

        private fun handleIfNull(text: String?): String {
            return text ?: context.getString(R.string.not_acquired)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSavedBreweryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class SavedBreweryDiffCallback(
        private val old: List<Brewery>,
        private val new: List<Brewery>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size
        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]
            return old === new
        }
    }
}
