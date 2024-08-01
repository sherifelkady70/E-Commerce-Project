package com.route.data.datasource.cart

import com.route.data.api.webservice.CartWebService
import com.route.data.contracts.cart.CartDataSource
import com.route.data.executeAPI
import com.route.domain.models.Cart
import com.route.domain.models.Products
import javax.inject.Inject

class CartDataSourceImpl @Inject constructor(
    private val cartWebService : CartWebService
): CartDataSource {
    override suspend fun addProductToCart(token: String, productId: String): Cart<String>? {
        return executeAPI { cartWebService.addProductToCard(token,productId) }
            .data?.toCart()
    }

    override suspend fun updateCartProductQuantity(
        token: String,
        cartProductId: String,
        count: String,
    ): Cart<Products>? {
        return executeAPI { cartWebService.updateCartProductQuantity(token,cartProductId,count) }
            .data?.toCart()
    }

    override suspend fun getLoggedUserCart(token: String): Cart<Products>? {
        return executeAPI { cartWebService.getLoggedUserCart(token) }
            .data?.toCart()
    }

    override suspend fun removeSpecificCartItem(
        token: String,
        cartProductId: String,
    ): Cart<Products>? {
        return executeAPI { cartWebService.removeSpecificCartItem(token,cartProductId) }
            .data?.toCart()
    }

    override suspend fun deleteUserCart(token: String): String? {
        return executeAPI { cartWebService.deleteUserCart(token) }.data
    }
}