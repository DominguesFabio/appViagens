package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Usuario
import com.example.appviagens.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NovoLoginViewModel(private val usuarioRepository: UsuarioRepository): ViewModel()  {

    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var senha by mutableStateOf("")

    var nomeValido by mutableStateOf(true)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validaNome(){
        nomeValido = nome.isNotEmpty()
        if (!nomeValido){
            throw Exception("Necessário informar o nome")
        }
    }

    fun registrar(onSuccess: () -> Unit) {
        try {
            validaNome()
            val novoUsuario = Usuario(nome = nome, email = email, senha = senha)
            usuarioRepository.addUser(novoUsuario)
            onSuccess()
        } catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message ?: "Error")
            }
        }
    }
}