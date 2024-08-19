package com.example.routee_commerce.ui.home.fragments.wishlist

import androidx.lifecycle.ViewModel
import com.route.domain.usecases.cart.AddProductToCardUseCase
import com.route.domain.usecases.cart.GetLoggedUserCartUseCase
import com.route.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.route.domain.usecases.wishlist.GetLoggedUserWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val getLoggedUserCartUseCase: GetLoggedUserCartUseCase,
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase,
    private val addProductToCartUseCase : AddProductToCardUseCase,
    private val removeProductFromWishList: DeleteProductFromWishlistUseCase
) : ViewModel() {
}