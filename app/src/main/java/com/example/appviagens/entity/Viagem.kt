package com.example.appviagens.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Viagem")
data class Viagem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val destino: String,
    val classe: String,
    val dataIni: String,
    val dataFim: String,
    val idUsuario: Int
) {

}