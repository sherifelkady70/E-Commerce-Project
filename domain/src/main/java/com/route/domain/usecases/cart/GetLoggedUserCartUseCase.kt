package com.route.domain.usecases.cart

import com.route.domain.common.Resource
import com.route.domain.contracts.cart.CartRepository
import com.route.domain.models.Cart
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedUserCartUseCase @Inject constructor(
    private val cartRepo : CartRepository
) {

    suspend operator fun invoke(token:String) : Flow<Resource<Cart<Products>?>> {
        return cartRepo.getLoggedUserCart(token)
    }
}