package com.example.appviagens.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Usuario
import com.example.appviagens.repository.UsuarioRepository
import kotlinx.coroutines.launch

class ListaUsuarioViewModel (private val usuarioRepository: UsuarioRepository): ViewModel() {

    var usuarios: MutableState<List<Usuario>> = mutableStateOf(listOf())

    var showDialogDelete = mutableStateOf(false)

    var deletarUsuario: Usuario? by mutableStateOf(null)

    fun loadAllUsuarios(){
        viewModelScope.launch {
            usuarios.value = usuarioRepository.loadAllUsers()
        }
    }

    fun deleteUsuario() {
        viewModelScope.launch {
            deletarUsuario?.let {
                usuarioRepository.delete(it)
                loadAllUsuarios()
            }
        }
    }
}