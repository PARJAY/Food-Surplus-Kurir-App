package com.example.dummyfirebaseauth

import android.app.Application
import com.example.kurrirapps.di.AppModuleImpl

class MyApp: Application() {

    companion object {
        lateinit var appModule: AppModuleImpl
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }
}