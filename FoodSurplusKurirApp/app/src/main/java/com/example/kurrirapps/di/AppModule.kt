package com.example.kurrirapps.di

import com.example.kurrirapps.data.repository.HotelRepositoryImpl
import com.example.kurrirapps.data.repository.KatalisRepositoryImpl
import com.example.kurrirapps.data.repository.KurirRepositoryImpl
import com.example.kurrirapps.data.repository.PesananRepositoryImpl
import com.example.kurrirapps.presentation.pesanan.PesananRepository
import com.google.firebase.storage.FirebaseStorage

interface AppModule {
    val pesananRepositoryImpl : PesananRepositoryImpl
    val katalisRepository : KatalisRepositoryImpl
    val pesananRepository : PesananRepository
    val hotelRepository : HotelRepositoryImpl
    val kurirRepositoryImpl : KurirRepositoryImpl
    val storage : FirebaseStorage
}