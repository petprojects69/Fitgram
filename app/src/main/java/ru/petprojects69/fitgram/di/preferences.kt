package ru.petprojects69.fitgram.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val PREFERENCES_NAME = "MY_PREF"
val preferences = module {
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.applicationContext.getSharedPreferences(
            PREFERENCES_NAME, Context.MODE_PRIVATE
        )
    }

    single { provideSharedPref(androidApplication()) }
    single<SharedPreferences.Editor> { get<SharedPreferences>().edit() }
}