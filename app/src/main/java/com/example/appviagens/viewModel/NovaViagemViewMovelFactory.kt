package com.example.appviagens.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagens.database.AppDatabase
import com.example.appviagens.repository.ViagemRepository

class NovaViagemViewMovelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).viagemDao()
        val viagemRepository = ViagemRepository(dao)
        return CriarNovaViagem(viagemRepository) as T
    }
}