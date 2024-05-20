package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: String,
    val image: String,
    val name: String,
    val slug: String
) : Parcelable