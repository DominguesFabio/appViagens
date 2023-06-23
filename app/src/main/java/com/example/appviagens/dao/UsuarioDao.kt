package com.example.appviagens.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appviagens.entity.Usuario


@Dao
interface UsuarioDao {
    @Insert
    fun insert(usuario : Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)

    @Query("select * from usuario u order by u.nome ")
    suspend fun getAll(): List<Usuario>

    @Query("select * from usuario u where u.nome = :nome")
    suspend fun findByName(nome: String): Usuario?

}