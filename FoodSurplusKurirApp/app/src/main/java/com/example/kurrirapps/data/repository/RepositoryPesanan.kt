package com.example.kurrirapps.data.repository

import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.tools.FirebaseResult

interface RepositoryPesanan {

    suspend fun getPesananList(callback: (FirebaseResult<List<PesananModel>>) -> Unit)
}