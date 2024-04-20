package com.example.dummyfirebaseauth.di

import com.example.kurrirapps.data.repository.KurirRepositoryImpl
import com.example.kurrirapps.presentation.pesanan.PesananRepository
import com.google.firebase.storage.FirebaseStorage

interface AppModule {
    val pesananRepositoryImpl : PesananRepository
    val kurirRepositoryImpl : KurirRepositoryImpl
    val storage : FirebaseStorage
}