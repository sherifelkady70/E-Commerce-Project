package com.route.data.contracts.wishlist

import com.route.data.api.model.wishlist.WishlistDTO
import com.route.data.api.model.wishlist.WishlistResponse

interface WishlistDataSource {
    suspend fun addProductToWishlist(token : String,productId: String)
    : WishlistResponse<List<String>?>

    suspend fun deleteProductFromWishlist(token : String,productId :String)
    : WishlistResponse<List<String>?>

    suspend fun getLoggedUserWishlist(token : String)
    : WishlistResponse<List<WishlistDTO>>
}