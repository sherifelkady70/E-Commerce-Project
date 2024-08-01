package com.route.domain.contracts.cart

import com.route.domain.common.Resource
import com.route.domain.models.Cart
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addProductToCart(token: String, productId: String): Flow<Resource<Cart<String>?>>

    suspend fun updateCartProductQuantity(
        token: String,
        cartProductId: String,
        count: String,
    ): Flow<Resource<Cart<Products>?>>

    suspend fun getLoggedUserCart(token: String): Flow<Resource<Cart<Products>?>>

    suspend fun removeSpecificCartItem(
        token: String,
        cartProductId: String,
    ): Flow<Resource<Cart<Products>?>>

    suspend fun deleteUserCart(token: String): Flow<Resource<String?>>
}