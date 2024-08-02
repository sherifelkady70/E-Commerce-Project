package com.route.domain.usecases.wishlist

import com.route.domain.common.Resource
import com.route.domain.contracts.wishlist.WishlistRepository
import com.route.domain.models.WishlistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedUserWishlist @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {

    suspend operator fun invoke(token:String) : Flow<Resource<List<WishlistItem>?>> {
        return wishlistRepository.getLoggedUserWishlist(token)
    }
}