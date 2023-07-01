package com.example.appviagens.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Despesa
import com.example.appviagens.repository.DespesaRepository
import kotlinx.coroutines.launch

class ListaDespesaViewModel (private val despesaRepository: DespesaRepository): ViewModel() {
    var despesa: MutableState<List<Despesa>> = mutableStateOf(listOf())

    fun loadAllDespesa(idViagem: Int) {
        viewModelScope.launch {
            despesa.value = despesaRepository.findByViagem(idViagem)
        }
    }
}