package com.rafaelbandeeira.contacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelbandeeira.contacts.R
import com.rafaelbandeeira.contacts.data.Contact
import com.rafaelbandeeira.contacts.databinding.ContactItemBinding

class ContactAdapter(
    private val list: MutableList<Contact> = mutableListOf()
) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<Contact>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ContactAdapterViewHolder(binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvName = binding.tvContactName
        private val tvPhone = binding.tvContactPhone
        private val ivPhotograph = binding.ivPhotograph

        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}