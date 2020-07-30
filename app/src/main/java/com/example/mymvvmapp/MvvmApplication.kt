package com.example.mymvvmapp

import android.app.Application
import com.example.mymvvmapp.data.db.AppDatabase
import com.example.mymvvmapp.data.network.MyApi
import com.example.mymvvmapp.data.network.NetworkConnectionInterceptor
import com.example.mymvvmapp.data.repositories.QuotesRepository
import com.example.mymvvmapp.data.repositories.UserRepository
import com.example.mymvvmapp.ui.auth.AuthViewModelFactory
import com.example.mymvvmapp.ui.home.profile.ProfileViewModelFactory
import com.example.mymvvmapp.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MvvmApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { QuotesRepository(instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

        bind() from provider { QuotesViewModelFactory(instance()) }

    }
}