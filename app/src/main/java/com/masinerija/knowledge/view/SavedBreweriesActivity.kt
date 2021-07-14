package com.masinerija.knowledge.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.masinerija.knowledge.R
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.databinding.ActivityBreweriesBinding
import com.masinerija.knowledge.databinding.ActivitySavedBreweriesBinding
import com.masinerija.knowledge.utils.toast
import com.masinerija.knowledge.view.adapter.BreweryItemAdapter
import com.masinerija.knowledge.view.adapter.SavedBreweryItemAdapter
import com.masinerija.knowledge.viewmodel.BreweriesViewModel
import com.masinerija.knowledge.viewmodel.SavedBreweriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedBreweriesActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySavedBreweriesBinding.inflate(layoutInflater) }
    private val viewModel: SavedBreweriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = SavedBreweryItemAdapter(this::editBrewery)
        binding.recyclerSavedBreweries.adapter = adapter
        val manager = GridLayoutManager(this, 1)
        binding.recyclerSavedBreweries.layoutManager = manager
    }

    private fun editBrewery(brewery: Brewery) {
        toast(getString(R.string.toast_open_edit))
    }

    private fun observeViewModel() {
        viewModel.breweriesObservable.observe(
            this, {
                it?.let { list ->
                    updateAdapter(list)
                }
            }
        )
    }

    private fun updateAdapter(breweries: List<Brewery>) {
        (binding.recyclerSavedBreweries.adapter as SavedBreweryItemAdapter)
            .setData(breweries)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, SavedBreweriesActivity::class.java)
    }
}