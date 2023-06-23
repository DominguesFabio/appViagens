package com.example.appviagens.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appviagens.database.AppDatabase
import com.example.appviagens.repository.UsuarioRepository

class NovoLoginViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).usuarioDao()
        val usuarioRepository = UsuarioRepository(dao)
        return NovoLoginViewModel(usuarioRepository) as T
    }
}