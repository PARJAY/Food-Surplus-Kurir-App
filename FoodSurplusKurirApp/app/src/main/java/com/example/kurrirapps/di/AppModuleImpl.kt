package com.example.dummyfirebaseauth.di

import com.example.kurrirapps.data.repository.PesananRepositoryImpl
import com.example.kurrirapps.model.Pesanan
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.ktx.storage

class AppModuleImpl: AppModule {
    private val db = Firebase.firestore
    override val storage = com.google.firebase.ktx.Firebase.storage("gs://nofwa-indonesia.appspot.com")

    override val pesananRepositoryImpl: PesananRepositoryImpl by lazy {
        PesananRepositoryImpl(db)
    }
}