package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.MutableStateFlow

class HomeContract {

    interface ViewModel {
        fun doAction() : Action
        val event : LiveData<Event>
        val state : MutableStateFlow<State>
    }

    sealed class Action { //intent

    }

    sealed class Event {

    }

    sealed class State {

    }
}