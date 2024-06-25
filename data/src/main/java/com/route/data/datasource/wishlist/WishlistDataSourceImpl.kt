package com.route.data.datasource.wishlist


import com.route.data.api.webservice.wishlist.WishlistWebService
import com.route.data.contracts.wishlist.WishlistDataSource
import com.route.domain.models.WishlistResponse
import com.route.data.executeAPI
import com.route.domain.models.WishlistItem
import javax.inject.Inject

class WishlistDataSourceImpl @Inject constructor(
    private val wishlistWebService: WishlistWebService
): WishlistDataSource {
    override suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        val response = executeAPI { wishlistWebService.addProductToWishlist(token,productId) }
        return WishlistResponse(response.status,response.message,response.data)
    }

    override suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        val response = executeAPI{ wishlistWebService.deleteProductFromWishlist(token,productId) }
        return WishlistResponse(response.status, response.message, response.data)
    }

    override suspend fun getLoggedUserWishlist(token: String): List<WishlistItem>? {
        return wishlistWebService.getLoggedUserWishlist(token).data?.map {
            it.toWishlist()
        }
    }


}