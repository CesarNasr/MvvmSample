package com.example.mymvvmapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mymvvmapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
        val user = repository.getUser()
}
