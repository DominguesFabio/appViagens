package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appviagens.entity.Viagem
import com.example.appviagens.repository.ViagemRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CriarNovaViagem (private val viagemRepository: ViagemRepository): ViewModel(){
    var destino by mutableStateOf("")
    var classificacao by mutableStateOf("")
    var dataIni by mutableStateOf("")
    var dataFim by mutableStateOf("")


    var destinoValido by mutableStateOf(true)
    var classificacaoValida by mutableStateOf(true)
    var dataIniValida by mutableStateOf(true)
    var dataFimValida by mutableStateOf(true)


    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validaCampos() {
        destinoValido = destino.isNotEmpty()
        if (!destinoValido) {
            throw Exception("Necessita informar o destino")
        }
        classificacaoValida = classificacao.isNotEmpty()
        if (!classificacaoValida) {
            throw Exception("Necessita informar a classificação da viagem")
        }
        dataIniValida = dataIni.isNotEmpty()
        if (!dataIniValida) {
            throw Exception("Necessário informar a data do incio da viagem")
        }
        dataFimValida = dataFim.isNotEmpty()
        if (!dataFimValida) {
            throw Exception("Necessário informar a data do fim da viagem")
        }

    }

    fun criarNovaViagem(idUsuario: Int, onSuccess: () -> Unit) {
        try {
            validaCampos()
            val Viagem = Viagem(destino = destino, classe = classificacao, dataIni = dataIni,
                                    dataFim = dataFim, idUsuario = idUsuario)
            viagemRepository.addViagem(Viagem)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Erro")
            }
        }

    }
}