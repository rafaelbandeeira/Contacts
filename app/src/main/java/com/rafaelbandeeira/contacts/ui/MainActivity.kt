package com.rafaelbandeeira.contacts.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact
import com.rafaelbandeeira.contacts.databinding.DrawerMenuBinding
import com.rafaelbandeeira.contacts.ui.ContactDetail.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val binding by lazy { DrawerMenuBinding.inflate(LayoutInflater.from(this)) }
    private val _adapter = ContactAdapter(listener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDrawer()
        fetchListContact()
        bindViews()
    }

    private fun fetchListContact() {
        val list = arrayListOf(
            Contact(
                name = "Rafael Bandeira",
                phone = "71 32594611",
                photograph = "img.jpg"
            )
        )
        getInstanceSharedPreferences().edit {
            val json = Gson().toJson(list)
            putString("contacts", json)
            commit()
        }
    }

    private fun getInstanceSharedPreferences() : SharedPreferences {
        return getSharedPreferences("com.rafaelbandeeira.contacts.PREFERENCES", Context.MODE_PRIVATE)
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
        updateList()
    }

    private fun getListContacts() : List<Contact> {
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    fun updateList() {
        val list = getListContacts()
        _adapter.updateList(list)
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

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}