package com.route.data.contracts.cart

import com.route.domain.models.Cart

interface CartDataSource {
    suspend fun addProductToCart(
        token : String,
        productId : String
    ) : Cart<String>
}