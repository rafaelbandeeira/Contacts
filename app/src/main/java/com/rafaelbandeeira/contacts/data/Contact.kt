package com.rafaelbandeeira.contacts.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var name: String,
    var phone: String,
    var photograph: String
) : Parcelable
