package com.example.appviagens.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appviagens.entity.Viagem


@Dao
interface ViagemDao {

    @Insert
    fun insert(viagem: Viagem)

    @Update
    suspend fun update(viagem: Viagem)

    @Delete
    suspend fun delete(viagem: Viagem)

    @Query("select * from viagem t order by t.destino ")
    suspend fun getAll(): List<Viagem>

    @Query("select * from viagem t where t.destino = :destino")
    suspend fun findByDestination(destino: String): Viagem

    @Query("select * from viagem t where t.idUsuario = :idUsuario")
    suspend fun findById(idUsuario: Int):  List<Viagem>

}