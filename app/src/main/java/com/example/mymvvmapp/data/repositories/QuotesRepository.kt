package com.example.mymvvmapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymvvmapp.data.db.AppDatabase
import com.example.mymvvmapp.data.db.entities.Quote
import com.example.mymvvmapp.data.network.MyApi
import com.example.mymvvmapp.data.network.SafeApiRequest
import com.example.mymvvmapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class QuotesRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()


    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }


    suspend fun getQuotes(): LiveData<List<Quote>>{
     return withContext(Dispatchers.IO){
         fetchQuotes()
         db.getQuoteDao().getQuotes()
     }
    }

    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val response = apiRequest{
              api.getQuotes()
            }
            quotes.postValue(response.quotes)
        }

//        Coroutines.io {
//            db.getQuoteDao().getQuotes()
//        }
    }



    private fun isFetchNeeded(): Boolean {
        return true
    }
}