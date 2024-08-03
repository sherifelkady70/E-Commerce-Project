package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import com.example.routee_commerce.model.WishListItem
import com.example.routee_commerce.ui.base.ViewMessage
import com.route.domain.models.Cart
import com.route.domain.models.CartItem
import com.route.domain.models.Category
import com.route.domain.models.Products
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
    }

    sealed class State {
        data object Idle : State()
        data object Loading : State()
        data class Success(
            val categoriesList: List<Category>? = null,
            val mostSellingProductsList: List<Products>? = null,
            val categoryProductsList: List<Products>? = null,
            val wishList: List<WishListItem>? = null,
            val cart: List<CartItem<Products>>? = null,
        ) : State()

    }
}