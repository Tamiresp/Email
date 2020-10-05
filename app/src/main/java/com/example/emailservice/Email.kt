package com.example.emailservice

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Email(
    val title: String,
    val subject: String
): Parcelable