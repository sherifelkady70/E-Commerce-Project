package com.route.data.repository.wishlist

import com.route.data.contracts.wishlist.WishlistDataSource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.wishlist.WishlistRepository
import com.route.domain.models.WishlistItem
import com.route.domain.models.WishlistResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val wishlistDataSource: WishlistDataSource
): WishlistRepository {
    override suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): Flow<Resource<WishlistResponse<List<String>?>>> {
        val response = toFlow { wishlistDataSource.addProductToWishlist(token,productId) }
        return response
    }

    override suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): Flow<Resource<WishlistResponse<List<String>?>>> {
       return toFlow { wishlistDataSource.deleteProductFromWishlist(token,productId) }
    }

    override suspend fun getLoggedUserWishlist(token: String): Flow<Resource<List<WishlistItem>?>> {
       return toFlow { wishlistDataSource.getLoggedUserWishlist(token) }
    }
}