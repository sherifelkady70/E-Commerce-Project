package com.route.domain.common

data class ServerErrorDC(
    val stateMessage : String?=null,
    val serverError : String?=null,
    val httpEx : Throwable?=null
) : Throwable(serverError , httpEx)
