package com.route.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WishlistItem(
    val imageCover: String? = null,
    val title: String? = null,
    val price: Int? = null,
    val priceAfterDiscount: Int? = null,
    val id: String? = null,
    val slug: String? = null,
) : Parcelable
