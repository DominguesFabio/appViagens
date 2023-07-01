package com.example.appviagens.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagens.database.AppDatabase
import com.example.appviagens.repository.DespesaRepository

class NovaDespesaViewModelFactory (val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).despesaDao()
        val despesaRepository = DespesaRepository(dao)
        return CriarNovaDespesa(despesaRepository) as T
    }
}