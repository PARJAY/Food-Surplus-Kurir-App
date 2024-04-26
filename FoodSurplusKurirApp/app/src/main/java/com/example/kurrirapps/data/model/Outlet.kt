package com.example.kurrirapps.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outlet")
data class Outlet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var outletName: String
)
