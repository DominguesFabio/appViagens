package com.example.appviagens.screen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appviagens.viewModel.NovoLoginViewModel
import com.example.appviagens.viewModel.NovoLoginViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NovoLogin(onAfterSave: () -> Unit, onBack:() -> Unit){
    val application = LocalContext.current.applicationContext as Application
    val viewModel: NovoLoginViewModel = viewModel(
        factory = NovoLoginViewModelFactory(application)
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


    Scaffold(scaffoldState = scaffoldState ) {
        Text(text = "Login", style = TextStyle(fontSize = 40.sp), fontWeight =  FontWeight.Bold)
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.nome = it},
                isError = !viewModel.nomeValido,
                label = {
                    Text(text = "Nome")
                }
            )
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it},
                label = {
                    Text(text = "E-mail")
                }
            )
            OutlinedTextField(
                value = viewModel.senha,
                onValueChange = { viewModel.senha = it},
                label = {
                    Text(text = "Senha")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.registrar(onSuccess = {
                        onAfterSave()
                        }
                    )
                })
                {
                    Text(text = "Salvar")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        onBack()
                    }) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}