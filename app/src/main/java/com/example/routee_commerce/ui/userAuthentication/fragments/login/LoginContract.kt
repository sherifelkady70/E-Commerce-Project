package com.example.routee_commerce.ui.userAuthentication.fragments.login

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow

class LoginContract {
    interface LoginViewModel{
        val event : LiveData<Event>
        val state : StateFlow<State>
        fun doAction(action : Action)
    }
    sealed class Action{
        data object Login : Action()
    }
    sealed class Event{

    }
    sealed class State{

    }
}