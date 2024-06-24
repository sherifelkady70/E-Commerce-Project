package com.route.data.contracts.wishlist

import com.route.data.api.model.wishlist.WishlistResponse
import com.route.domain.models.WishlistItem

interface WishlistDataSource {
    suspend fun addProductToWishlist(token : String,productId: String)
    : WishlistResponse<List<String>?>

    suspend fun deleteProductFromWishlist(token : String,productId :String)
    : WishlistResponse<List<String>?>

    suspend fun getLoggedUserWishlist(token : String)
    : List<WishlistItem>?
}