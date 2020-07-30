package com.example.mymvvmapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmapp.data.repositories.QuotesRepository
import com.example.mymvvmapp.data.repositories.UserRepository
import com.example.mymvvmapp.ui.home.profile.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(private val repository: QuotesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}