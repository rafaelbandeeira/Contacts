package com.rafaelbandeeira.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact
import com.rafaelbandeeira.contacts.databinding.DrawerMenuBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { DrawerMenuBinding.inflate(LayoutInflater.from(this)) }
    private val _adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initDrawer()
        bindViews()
        updateList()
    }

    private fun initDrawer() {
        val drawerLayout = binding.drawerLayout
        val toolbar = binding.included.toolbar
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_menu,
            R.string.close_menu
        )
        toggle.syncState()
    }

    private fun bindViews() {
        binding.included.rvList.apply {
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