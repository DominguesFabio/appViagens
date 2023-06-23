package com.example.appviagens.repository

import com.example.appviagens.dao.ViagemDao
import com.example.appviagens.entity.Viagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViagemRepository(private val viagemDao: ViagemDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addViagem(viagem: Viagem) {
        coroutine.launch(Dispatchers.IO) {
            viagemDao.insert(viagem)
        }
    }

    fun delete(viagem: Viagem) {
        coroutine.launch(Dispatchers.IO) {
            viagemDao.delete(viagem)
        }
    }

    suspend fun loadAllViagens(): List<Viagem> {
        return viagemDao.getAll()
    }
    suspend fun findById(userId: Int): List<Viagem> =
        viagemDao.findById(userId)
}