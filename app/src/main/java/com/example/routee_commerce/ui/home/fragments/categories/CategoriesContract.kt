package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
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

    }

    sealed class State {

    }
}