package com.route.domain.contracts.wishlist

import com.route.domain.common.Resource

interface WishlistRepository {
    suspend fun addProductToWishlist(
        token : String,
        productId : String
    ) : Flow<Resource<Wishlis>>
}