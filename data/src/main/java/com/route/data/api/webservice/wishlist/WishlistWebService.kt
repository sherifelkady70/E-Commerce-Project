package com.route.data.api.webservice.wishlist

import com.route.data.api.model.wishlist.WishlistDTO
import com.route.data.api.model.wishlist.WishlistResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface WishlistWebService {

    @FormUrlEncoded
    @POST("/api/v1/wishlist")
    suspend fun addProductToWishlist(
        @Header("token") token : String,
        @Field("productId") productId : String
    ):WishlistResponse<List<String>?>
    @DELETE("/api/v1/wishlist/{productId}")
    suspend fun deleteProductFromWishlist(
        @Header("token") token : String,
        @Path("productId") productId : String
    ): WishlistResponse<List<String>?>
    @GET("/api/v1/wishlist")
    suspend fun getLoggedUserWishlist(
        @Header("token") token : String
    ):WishlistResponse<List<WishlistDTO>?>
}