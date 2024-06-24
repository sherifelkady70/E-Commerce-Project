package com.route.data.datasource.wishlist

import com.route.data.api.model.wishlist.WishlistResponse
import com.route.data.api.webservice.wishlist.WishlistWebService
import com.route.data.contracts.wishlist.WishlistDataSource
import com.route.domain.models.WishlistItem
import javax.inject.Inject

class WishlistDataSourceImpl @Inject constructor(
    private val wishlistWebService: WishlistWebService
): WishlistDataSource{
    override suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        return wishlistWebService.addProductToWishlist(token,productId)
    }

    override suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        return wishlistWebService.deleteProductFromWishlist(token,productId)
    }

    override suspend fun getLoggedUserWishlist(token: String): List<WishlistItem>? {
        return wishlistWebService.getLoggedUserWishlist(token).data?.map {
            it.toWishlist()
        }
    }


}