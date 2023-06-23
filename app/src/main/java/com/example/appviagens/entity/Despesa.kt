package com.example.appviagens.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Despesa")
data class Despesa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val despesa: Float,
    val viagemId: Int
)