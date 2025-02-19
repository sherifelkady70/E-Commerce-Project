package com.route.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Brand(
	val image: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null
) : Parcelable