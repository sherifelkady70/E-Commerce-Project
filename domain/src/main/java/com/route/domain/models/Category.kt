package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: String?=null,
    val image: String?=null,
    val name: String?=null,
    val slug: String?=null
) : Parcelable