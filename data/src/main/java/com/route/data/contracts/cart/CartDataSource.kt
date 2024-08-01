package com.route.data.contracts.cart

import com.route.domain.models.Cart
import com.route.domain.models.Products

interface CartDataSource {
    suspend fun addProductToCart(
        token : String,
        productId : String
    ) : Cart<String>

    suspend fun updateCartProductQuantity(
        token : String,
        cartProductId : String ,
        count : String
    ) : Cart<Products>

    suspend fun getLoggedUserCart(
        token: String
    ) : Cart<Products>

    suspend fun removeSpecificCartItem(
        token: String ,
        cartProductId : String
    ) : Cart<Products>

    suspend fun deleteUserCart(
        token: String
    ) : String
}