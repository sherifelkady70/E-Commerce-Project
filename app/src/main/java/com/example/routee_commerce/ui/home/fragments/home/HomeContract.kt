package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import com.route.domain.models.Category
import kotlinx.coroutines.flow.StateFlow

class HomeContract {

    interface ViewModel {
        fun doAction(action : Action)
        val event : LiveData<Event>
        val state : StateFlow<State>
    }

    sealed class Action { //intent
        data object initPage : Action()
    }

    sealed class Event {

    }

    sealed class State {
        data class Loading (val stateOfLoading : Boolean? = null) : State()
        data class Success (val categoriesList : List<Category>? = null)
            : State()
    }
}