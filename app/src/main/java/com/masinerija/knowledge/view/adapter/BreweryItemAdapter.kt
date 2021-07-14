package com.masinerija.knowledge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masinerija.knowledge.R
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.databinding.ListItemBreweryBinding

typealias BreweryClickListener = (Brewery) -> Unit

class BreweryItemAdapter(
    var clickListener: BreweryClickListener
): RecyclerView.Adapter<BreweryItemAdapter.ViewHolder>() {

    private val items = ArrayList<Brewery>()
    private val existingIds = ArrayList<Int>()

    fun setData(fetchedBreweries: List<Brewery>, savedIds: List<Int>?) {
        val diffCallback = BreweryDiffCallback(items, fetchedBreweries)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(fetchedBreweries)
        existingIds.clear()
        savedIds?.let {
            existingIds.addAll(it)
        }
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = items[position], existingIds)
        holder.itemView.tag = position

        holder.binding.btnSave.setOnClickListener{
            clickListener(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(val binding: ListItemBreweryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Brewery, existingIds: ArrayList<Int>){
            binding.txtBrewery.text = binding.root.context.getString(R.string.brewery_info, item.name, item.city)
            binding.btnSave.isVisible = checkIfItemExists(item, existingIds)
        }

        private fun checkIfItemExists(item: Brewery, existingIds: ArrayList<Int>): Boolean {
            var exists = false
            existingIds.forEach{ id ->
                if (id == item.breweryId){
                    exists = true
                }
            }
            return !exists
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBreweryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class BreweryDiffCallback(private val old: List<Brewery>, private val new: List<Brewery>) :
    DiffUtil.Callback(){
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

