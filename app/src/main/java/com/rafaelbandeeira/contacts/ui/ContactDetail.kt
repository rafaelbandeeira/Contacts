package com.rafaelbandeeira.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact
import com.rafaelbandeeira.contacts.databinding.ActivityContactDetailBinding

class ContactDetail(private var contact: Contact? = null) : AppCompatActivity() {
    private val binding by lazy { ActivityContactDetailBinding.inflate(layoutInflater) }

    companion object {
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()
        getExtras()
        bindViews()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindViews() {
        binding.apply {
            tvContactName.text = contact?.name
            tvContactPhone.text = contact?.phone
        }
    }

    private fun getExtras() {
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}