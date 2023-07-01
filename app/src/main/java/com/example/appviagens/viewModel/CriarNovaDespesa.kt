package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Despesa
import com.example.appviagens.repository.DespesaRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CriarNovaDespesa(private val despesaRepository: DespesaRepository): ViewModel() {
    var nome by mutableStateOf("")
    var despesa by mutableStateOf(0.00f)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun criarNovaDespesa(idViagem: Int, onSuccess: () -> Unit) {
        try {
            val novaDespesa = Despesa(nome = nome, despesa = despesa, viagemId = idViagem)
            despesaRepository.addDespesa(novaDespesa)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Erro")
            }
        }

    }
}