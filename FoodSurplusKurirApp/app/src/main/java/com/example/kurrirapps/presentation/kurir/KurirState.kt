package com.example.kurrirapps.presentation.kurir

import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.logic.StatusKurir


data class KurirState(
    val kurirState: KurirModel = KurirModel(statusKurir = StatusKurir.IDLE)
)