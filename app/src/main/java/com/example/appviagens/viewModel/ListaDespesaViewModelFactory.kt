package com.example.appviagens.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagens.database.AppDatabase
import com.example.appviagens.repository.DespesaRepository

class ListaDespesaViewModelFactory (val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).despesaDao()
        val despesaRepository = DespesaRepository(dao)
        return ListaDespesaViewModel(despesaRepository) as T
    }
}