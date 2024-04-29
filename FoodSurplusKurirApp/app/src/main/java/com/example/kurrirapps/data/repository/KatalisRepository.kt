package com.example.kurrirapps.data.repository

import com.example.kurrirapps.data.model.KatalisModel
import com.example.kurrirapps.tools.FirebaseResult

interface KatalisRepository {
    suspend fun getKatalisList(callback: (FirebaseResult<List<KatalisModel>>) -> Unit)
}
