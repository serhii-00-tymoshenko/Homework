package com.example.homework.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val singer: Singer,
    val songName: String,
    val songDuration: Int,
    val albumCover: Int,
    val id: Int
) : Parcelable