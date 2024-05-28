package com.route.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Products(
	val sold: Int? = null,
	val images: List<String?>? = null,
	val quantity: Int? = null,
	val imageCover: String? = null,
	val description: String? = null,
	val title: String? = null,
	val ratingsQuantity: Int? = null,
	val ratingsAverage: Double? = null,
	val createdAt: String? = null,
	val price: Int? = null,
	val id: String? = null,
	val subcategory: List<Category?>? = null,
	val category: Category? = null,
	val brand: Brand? = null,
	val slug: String? = null,
	val updatedAt: String? = null
) : Parcelable