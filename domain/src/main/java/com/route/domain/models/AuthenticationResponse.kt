package com.route.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthenticationResponse(
    val user: User? = null,
    val token: String? = null
) : Parcelable