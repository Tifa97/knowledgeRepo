package com.masinerija.knowledge.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.masinerija.knowledge.R
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.databinding.ActivityBreweriesBinding
import com.masinerija.knowledge.databinding.ActivityMainBinding
import com.masinerija.knowledge.view.adapter.BreweryItemAdapter
import com.masinerija.knowledge.viewmodel.BreweriesViewModel
import com.masinerija.knowledge.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreweriesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBreweriesBinding.inflate(layoutInflater) }
    private val viewModel: BreweriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = BreweryItemAdapter(this::saveBrewery)
        binding.recyclerBreweries.adapter = adapter
        val manager = GridLayoutManager(this, 1)
        binding.recyclerBreweries.layoutManager = manager
    }

    private fun observeViewModel() {
        viewModel.breweriesObservable.observe(
            this,
            {
                it?.let {
                    updateAdapter(it)
                }
            }
        )
    }

    private fun updateAdapter(breweries: List<Brewery>) {
        (binding.recyclerBreweries.adapter as BreweryItemAdapter).submitList(breweries)
    }

    private fun saveBrewery(brewery: Brewery) {
        val dialog = AlertDialog.Builder(this)
            .setMessage(R.string.save_brewery_msg)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.insertBrewery(brewery)
            }
            .setNegativeButton(R.string.no) { _, _ -> }
            .create()
        dialog.show()
    }

    companion object{
        fun newIntent(context: Context) = Intent(context, BreweriesActivity::class.java)
    }
}