package com.example.mymvvmapp.data.network.responses

import com.example.mymvvmapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)