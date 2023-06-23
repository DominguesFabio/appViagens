package com.example.appviagens.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Viagem
import com.example.appviagens.repository.ViagemRepository
import kotlinx.coroutines.launch

class ListaViagensViewModel (private val viagemRepository: ViagemRepository): ViewModel() {

    var viagens: MutableState<List<Viagem>> = mutableStateOf(listOf())

    fun loadAllViagens(idUsuario: Int) {
        viewModelScope.launch {
            viagens.value = viagemRepository.findById(idUsuario)
        }
    }
}