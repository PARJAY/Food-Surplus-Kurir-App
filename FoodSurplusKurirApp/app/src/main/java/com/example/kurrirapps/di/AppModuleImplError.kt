//package com.example.dummyfirebaseauth.di
//
//import com.example.kurrirapps.data.repository.KurirRepositoryImpl
//import com.example.kurrirapps.data.repository.PesananRepositoryImpl
//import com.example.kurrirapps.di.AppModule
//import com.example.kurrirapps.presentation.pesanan.PesananRepository
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
//import com.google.firebase.storage.ktx.storage
//
//class AppModuleImplError : AppModule {
//    private val db = Firebase.firestore
//    override val storage = com.google.firebase.ktx.Firebase.storage("gs://nofwa-indonesia.appspot.com")
//
//    override val pesananRepositoryImpl: PesananRepositoryImpl by lazy {
//        PesananRepositoryImpl(db)
//    }
//    override val kurirRepositoryImpl: KurirRepositoryImpl by lazy {
//        KurirRepositoryImpl(db)
//    }
//
//    override val pesananRepository: PesananRepository by lazy {
//        PesananRepository(db)
//    }
//}