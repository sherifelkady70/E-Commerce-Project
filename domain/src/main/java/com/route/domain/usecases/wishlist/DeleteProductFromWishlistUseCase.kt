package com.route.domain.usecases.wishlist

import com.route.domain.common.Resource
import com.route.domain.contracts.wishlist.WishlistRepository
import com.route.domain.models.WishlistResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteProductFromWishlistUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {

    suspend operator fun invoke(token:String,productId:String)
    : Flow<Resource<WishlistResponse<List<String>?>>> {
        return wishlistRepository.deleteProductFromWishlist(token,productId)
    }
}