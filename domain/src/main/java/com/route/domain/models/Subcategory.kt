package com.route.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Subcategory(
	val name: String? = null,
	val id: String? = null,
	val category: String? = null,
	val slug: String? = null
) : Parcelable
