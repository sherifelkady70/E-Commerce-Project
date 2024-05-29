package com.route.data.api.model

import com.route.domain.models.Products

data class ProductsDTO(
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
	val subcategory: List<CategoryDTO?>? = null,
	val category: CategoryDTO? = null,
	val priceAfterDiscount: Int? = null,
	val brand: BrandDTO? = null,
	val slug: String? = null,
	val updatedAt: String? = null
){
	fun toProducts() : Products{
		return Products(sold,images,quantity
			,imageCover, description, title, ratingsQuantity, ratingsAverage
		,createdAt,price=price,id=id, subcategory=subcategory?.map { it?.toCategory()}?.toList(),
			category=category?.toCategory(),brand=brand?.toBrand(),updatedAt = updatedAt)
	}
}