package com.example.routee_commerce.ui.home.fragments.wishlist

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow

class WishListContract {

    interface WishListContact{
        val event : LiveData<Event>
        val state : StateFlow<State>

        fun doAction(action : Action)
    }
    sealed class Action{
        data class initPage(val token:String) : Action()
        data class addProdudctToCart(val token: String , val productId:String) : Action()
        data class removeProductFromWishList(val token: String , val productId: String) : Action()
    }
    sealed class Event{

    }
    sealed class State{

    }
}