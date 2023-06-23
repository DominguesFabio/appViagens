package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val usuarioRepository: UsuarioRepository): ViewModel() {
    var nome by mutableStateOf("")
    var senha by mutableStateOf("")

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()


    fun validateLogin(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            val usuario = usuarioRepository.findByName(nome)
            val result = usuario != null && usuario.senha == senha
            if (usuario != null) {
                onResult(usuario.id)
            }
            if (!result )
                _toastMessage.emit("Login Invalido")
        }

    }
}