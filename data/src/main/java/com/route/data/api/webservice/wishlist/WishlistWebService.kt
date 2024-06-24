package com.route.data.api.webservice.wishlist

import com.route.data.api.model.wishlist.WishlistResponse
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST

interface WishlistWebService {

    @POST("/api/v1/wishlist")
    suspend fun addProductToWishlist(
        @Header("token") token : String,
        @Field("productId") productId : String
    ):WishlistResponse<List<String>?>
}