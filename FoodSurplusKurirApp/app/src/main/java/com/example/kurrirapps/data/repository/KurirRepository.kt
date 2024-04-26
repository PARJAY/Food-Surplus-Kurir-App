package com.example.kurrirapps.data.repository

import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.tools.FirebaseResult

interface KurirRepository {
        suspend fun getPesananList(callback: (FirebaseResult<List<KurirModel>>) -> Unit, idCustomer : String)
}