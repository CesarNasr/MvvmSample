package com.example.mymvvmapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mymvvmapp.data.repositories.QuotesRepository
import com.example.mymvvmapp.utils.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}



