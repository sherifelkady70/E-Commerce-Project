package com.route.data.api.model.cart

import com.google.gson.annotations.SerializedName
import com.route.data.api.model.ProductsDTO
import com.route.domain.models.CartItem
import com.route.domain.models.Products

data class CartItemForProductDto(
    @field:SerializedName("product")
    val product: ProductsDTO? = null,
    @field:SerializedName("price")
    val price: Int? = null,
    @field:SerializedName("count")
    val count: Int? = null,
    @field:SerializedName("_id")
    val id: String? = null,
) {
    fun toCartItem(): CartItem<Products> {
        return CartItem(product?.toProducts(), price, count, id)
    }
}
