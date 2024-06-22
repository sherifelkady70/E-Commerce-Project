package com.route.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Subcategory(
	val metadata: Metadata? = null,
	val data: List<DataItem?>? = null,
	val results: Int? = null
) : Parcelable

@Parcelize
data class Metadata(
	val numberOfPages: Int? = null,
	val nextPage: Int? = null,
	val limit: Int? = null,
	val currentPage: Int? = null
) : Parcelable

@Parcelize
data class DataItem(
	val createdAt: String? = null,
	val name: String? = null,
	val id: String? = null,
	val category: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
) : Parcelable
