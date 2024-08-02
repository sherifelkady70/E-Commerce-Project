package com.route.domain.usecases.cart

import com.route.domain.common.Resource
import com.route.domain.contracts.cart.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserCart @Inject constructor(
    private val cartRepo : CartRepository
) {

    suspend operator fun invoke(token:String) : Flow<Resource<String?>> {
        return cartRepo.deleteUserCart(token)
    }
}