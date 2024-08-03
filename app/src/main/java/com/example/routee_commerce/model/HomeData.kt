package com.example.routee_commerce.model

import com.route.domain.models.Cart
import com.route.domain.models.CartItem
import com.route.domain.models.Category
import com.route.domain.models.Products

data class HomeData (
    val categoriesList : List<Category>?,
    val categoryProductsList : List<Products>?,
    val mostSellingProductsList : List<Products>?,
    val wishList : List<WishListItem>?,
    val cartList : List<CartItem<Products>>?
)