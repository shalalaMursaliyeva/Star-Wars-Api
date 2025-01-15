package com.example.starwars.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birthYear: String?,
    val created: String?,
    val edited: String?,
    val eyeColor: String?,
    val films: List<String>?,
    val gender: String?,
    val hairColor: String?,
    val height: String?,
    val homeworld: String?,
    val mass: String?,
    val name: String?,
    val skinColor: String?,
    val species: List<String>?,
    val starships: List<String>?,
    val url: String?,
    val vehicles: List<String>?
) : Parcelable
