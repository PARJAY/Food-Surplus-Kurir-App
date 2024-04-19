package com.example.dummyfirebaseauth.di

import com.example.kurrirapps.data.repository.PesananRepositoryImpl
import com.example.kurrirapps.model.Pesanan
import com.google.firebase.storage.FirebaseStorage

interface AppModule {
    val pesananRepositoryImpl : PesananRepositoryImpl
    val storage : FirebaseStorage
}