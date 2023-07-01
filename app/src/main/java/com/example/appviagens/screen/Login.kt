package com.example.appviagens.screen

import android.app.Application
import android.graphics.Paint.Style
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appviagens.ui.theme.Purple40
import com.example.appviagens.ui.theme.PurpleGrey80
import com.example.appviagens.viewModel.LoginViewModel
import com.example.appviagens.viewModel.LoginViewModelFactory
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun Login(onAfterLogin: (Int) -> Unit, onBack:() -> Unit, navController : NavController) {
    val application = LocalContext.current.applicationContext as Application


    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(application)
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


    Scaffold(scaffoldState = scaffoldState, backgroundColor = PurpleGrey80 ) {
        TopAppBar(title = { Text(text = "Tela de login") } )
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.nome,
                onValueChange = { viewModel.nome = it},
                label = {
                    Text(text = "Nome")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.senha,
                onValueChange = { viewModel.senha = it},
                label = {
                    Text(text = "Senha")
                }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.validateLogin(onResult = {userId ->
                        if (userId > 0) {
                            onAfterLogin(userId)
                        }
                    })
                    }) {
                        Text(text = "Login")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        onBack()
                    }) {
                    Text(text = "Voltar")
                }
            }
            TextButton(onClick = { navController.navigate("novoLogin") }) {
                Text(text = "Cadastrar")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Esqueci minha senha")
            }

        }
    }

}