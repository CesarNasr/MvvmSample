package com.example.mymvvmapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mymvvmapp.data.repositories.UserRepository
import com.example.mymvvmapp.utils.ApiException
import com.example.mymvvmapp.utils.Coroutines
import com.example.mymvvmapp.utils.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var email: String? = null
    var name: String? = null
    var passwordConfirm: String? = null

    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {

        authListener?.onStarted()

        if (email.isNullOrEmpty() || email.isNullOrEmpty()) {
            //
            authListener?.onFailure("Invalid email or password")

            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)

                    return@main

                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View) {

        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")

            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")

            return
        }


        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Password is required")

            return
        }

        if (password != passwordConfirm) {
            authListener?.onFailure("Password do not match")

            return
        }

        Coroutines.main {

            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)

                    return@main

                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}