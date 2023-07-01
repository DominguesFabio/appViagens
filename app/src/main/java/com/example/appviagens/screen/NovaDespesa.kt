package com.example.appviagens.screen

import android.R.id
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appviagens.viewModel.CriarNovaDespesa
import com.example.appviagens.viewModel.NovaDespesaViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NovaDespesa (despesaId: Int, onBack: () -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: CriarNovaDespesa = viewModel(
        factory = NovaDespesaViewModelFactory(application)
    )
    val ctx = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Long
            )
        }
    }
    val focusManager = LocalFocusManager.current
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.nome = it },
                label = {
                    Text(text = "Digite o nome da despesa")
                }
            )
            OutlinedTextField(
                value = viewModel.despesa.toString(),
                onValueChange = {viewModel.despesa = it.toFloat() },
                label = {
                    Text(text = "Valor da despesa")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.criarNovaDespesa(despesaId, onSuccess = {
                        onBack()
                    })
                }) {
                    Text(text = "Criar Nova Despesa!")
                }
            }
        }
    }
}