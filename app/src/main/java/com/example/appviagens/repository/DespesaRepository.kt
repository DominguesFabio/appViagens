package com.example.appviagens.repository

import com.example.appviagens.dao.DespesaDao
import com.example.appviagens.entity.Despesa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DespesaRepository (private val expenseDao: DespesaDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addDespesa(despesa: Despesa) {
        coroutine.launch(Dispatchers.IO) {
            expenseDao.insert(despesa)
        }
    }

    suspend fun findByViagem(viagemId: Int): List<Despesa> =
        expenseDao.getBugdet(viagemId)
}