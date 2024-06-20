package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
import com.example.routee_commerce.ui.base.ViewMessage
import com.route.domain.models.Category
import kotlinx.coroutines.flow.StateFlow
 class CategoriesContract {

    interface CategoriesViewModel {
        fun doAction(action : Action)

        val event : LiveData<Event>
        val state : StateFlow<State>
    }

    sealed class Action {
        data object InitPage : Action()
    }

    sealed class Event {
        data class ShowMessage(val message : ViewMessage) : Event()
        data class ShowLoading(val loading : Boolean)   : Event()
    }

    sealed class State {
        data object Loading : State()
        data class Success(val categoriesList : List<Category>? =null) : State()
    }
}