package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import com.example.routee_commerce.ui.base.ViewMessage
import com.route.domain.models.CartItem
import com.route.domain.models.Category
import com.route.domain.models.Products
import com.route.domain.models.WishlistItem
import kotlinx.coroutines.flow.StateFlow

class HomeContract {

    interface ViewModel {
        fun doAction(action : Action)

        val event : LiveData<Event>
        val state : StateFlow<State>
    }

    sealed class Action { //intent
        data class InitPage(val token: String) : Action()
        data class AddProductToCart(val token:String,val productId : String) : Action()
        data class AddProductToWishList(val token: String,val productId: String) : Action()
        data class RemoveProductFromWishList(val token: String,val productId: String) : Action()
    }

    sealed class Event {
        data class ShowMessage(val message : ViewMessage) : Event()
        data class ShowLoading(val loading : Boolean) : Event()

        data class ProductAddedToCartSuccess(val cartItems : List<CartItem<String>>?) : Event()
        data class ProductAddedToWishListSuccess(
            val message : String,
            val wishList : List<String>
        ) : Event()
        data class ProductRemovedFromWishListSuccess(
            val message : String,
            val wishList : List<String>
        ) : Event()
    }

    sealed class State {
        data object Idle : State()
        data object Loading : State()
        data class Success(
            val categoriesList: List<Category>? = null,
            val mostSellingProductsList: List<Products>? = null,
            val categoryProductsList: List<Products>? = null,
            val wishList: List<WishlistItem>? = null,
            val cart: List<CartItem<Products>>? = null,
        ) : State()

    }
}