package com.route.domain.usecases.cart

import com.route.domain.common.Resource
import com.route.domain.contracts.cart.CartRepository
import com.route.domain.models.Cart
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveSpecificCartItem @Inject  constructor(
    private val cartRepo : CartRepository
){

    suspend operator fun invoke(token:String,productId:String) : Flow<Resource<Cart<Products>?>> {
        return cartRepo.removeSpecificCartItem(token,productId)
    }

}