package com.route.data.api.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.route.domain.models.Subcategory

@Parcelize
data class SubcategoryDTO(
	val createdAt: String? = null,
	val name: String? = null,
	val id: String? = null,
	val category: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
) : Parcelable
{
	fun toSubCategory() : Subcategory {
		return Subcategory(name = name , id = id , category = category , slug = slug)
	}
}
