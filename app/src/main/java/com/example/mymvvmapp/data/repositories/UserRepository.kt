package com.example.mymvvmapp.data.repositories

import com.example.mymvvmapp.data.db.AppDatabase
import com.example.mymvvmapp.data.db.entities.User
import com.example.mymvvmapp.data.network.MyApi
import com.example.mymvvmapp.data.network.SafeApiRequest
import com.example.mymvvmapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { api.userLogin(email, password) }
        // bad practice since it makes this dependent on MyAPI() so we need to inject it
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest {
            api.userSignup(name, email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
    fun getUser() = db.getUserDao().getUser()


}