package com.example.routee_commerce.ui.home.fragments.categories

class CategoriesContract {

    interface CategoriesViewModel {
        fun doAction(action : Action)

    }

    sealed class Action {
        data object InitPage : Action()
    }

    sealed class Event {

    }

    sealed class State {

    }
}