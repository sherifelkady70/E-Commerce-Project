package com.route.domain.usecases.cart

import com.route.domain.common.Resource
import com.route.domain.contracts.cart.CartRepository
import com.route.domain.models.Cart
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddProductToCardUseCase @Inject constructor(
    private val cardRepo : CartRepository
) {
    suspend operator fun invoke(token:String , productId : String)
    : Flow<Resource<Cart<String>?>> {
        return cardRepo.addProductToCart(token, productId)
    }
}