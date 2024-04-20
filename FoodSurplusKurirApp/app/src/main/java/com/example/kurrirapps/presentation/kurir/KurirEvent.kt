package com.example.kurrirapps.presentation.kurir

import com.example.kurrirapps.data.model.KurirModel

sealed interface KurirEvent {
    data class CreateKurir(val kurirId : String, val customer: KurirModel): KurirEvent
    data class UpdateKurir(val kurirId : String, val customer: KurirModel): KurirEvent
    data class GetKurirById (val kurirId : String) : KurirEvent
    data class DeleteKurir(val kurir: KurirModel):KurirEvent
}