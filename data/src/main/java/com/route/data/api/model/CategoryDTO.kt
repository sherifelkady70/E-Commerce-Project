package com.route.data.api.model

import com.google.gson.annotations.SerializedName
import com.route.domain.models.Category

data class CategoryDTO(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
){
	fun toCategory() : Category { //to convert the model that is get from API Call  (CategoryDTO)
		                         // to the model that is in domain (Category)
		                         // because go to ui to display only this fields that is in (Category model)
		return Category(
			image = image ,
			id = id ,
			name = name ,
			slug = slug
		)
	}
}