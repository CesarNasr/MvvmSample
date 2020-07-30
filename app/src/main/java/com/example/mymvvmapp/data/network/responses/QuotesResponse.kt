package com.example.mymvvmapp.data.network.responses

import com.example.mymvvmapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)