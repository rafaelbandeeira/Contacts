package com.rafaelbandeeira.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact

class MainActivity : AppCompatActivity() {
    private val rvList by lazy { findViewById<RecyclerView>(R.id.rv_list) }
    private val _adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        bindViews()
        updateList()
    }

    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
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
        rvList.adapter = _adapter
        rvList.layoutManager = LinearLayoutManager(this)
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