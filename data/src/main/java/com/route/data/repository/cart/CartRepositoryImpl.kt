package com.route.data.repository.cart

import com.route.data.contracts.cart.CartDataSource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.cart.CartRepository
import com.route.domain.models.Cart
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataSource : CartDataSource
): CartRepository {
    override suspend fun addProductToCart(
        token: String,
        productId: String,
    ): Flow<Resource<Cart<String>?>> {
        return toFlow {cartDataSource.addProductToCart(token , productId) }
    }

    override suspend fun updateCartProductQuantity(
        token: String,
        cartProductId: String,
        count: String,
    ): Flow<Resource<Cart<Products>?>> {
       return toFlow { cartDataSource.updateCartProductQuantity(token,cartProductId,count) }
    }

    override suspend fun getLoggedUserCart(token: String): Flow<Resource<Cart<Products>?>> {
        return toFlow { cartDataSource.getLoggedUserCart(token) }
    }

    override suspend fun removeSpecificCartItem(
        token: String,
        cartProductId: String,
    ): Flow<Resource<Cart<Products>?>> {
        return toFlow { cartDataSource.removeSpecificCartItem(token,cartProductId) }
    }

    override suspend fun deleteUserCart(token: String): Flow<Resource<String?>> {
        return toFlow { cartDataSource.deleteUserCart(token) }
    }
}