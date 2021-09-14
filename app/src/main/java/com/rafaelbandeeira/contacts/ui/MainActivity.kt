package com.rafaelbandeeira.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact
import com.rafaelbandeeira.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val _adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bindViews()
        updateList()
    }

    private fun bindViews() {
        binding.rvList.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun updateList() {
        _adapter.updateList(
            arrayListOf(
                Contact(
                    name = "Rafael Bandeira",
                    phone = "71 32594611",
                    photograph = "img.jpg"
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                showToast(item)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(item: MenuItem) =
        Toast.makeText(this, "${item.title} foi clicado.", Toast.LENGTH_SHORT).show()
}