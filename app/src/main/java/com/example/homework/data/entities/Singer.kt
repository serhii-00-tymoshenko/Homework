package com.example.homework.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Singer(
    val fullName: String,
    val stageName: String,
    val bio: String
) : Parcelable
