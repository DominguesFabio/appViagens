package com.example.appviagens.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appviagens.entity.Despesa

@Dao
interface DespesaDao {
    @Insert
    fun insert(despesa: Despesa)

    @Update
    suspend fun update(despesa: Despesa)

    @Delete
    suspend fun delete(despesa: Despesa)

    @Query("select * from despesa e where e.viagemId = :viagemId")
    suspend fun getBugdet(viagemId: Int): List<Despesa>
}