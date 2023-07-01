package com.example.appviagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appviagens.screen.ListaDespesas
import com.example.appviagens.screen.ListaViagem
import com.example.appviagens.screen.Login
import com.example.appviagens.screen.NovaDespesa
import com.example.appviagens.screen.NovaViagem
import com.example.appviagens.screen.NovoLogin
import com.example.appviagens.screen.Sobre
import com.example.appviagens.screen.appBar
import com.example.appviagens.ui.theme.AppViagensTheme
import com.example.appviagens.viewModel.CriarNovaViagem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                 //   color =  MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "login" ){
        composable("login"){
            Login(
                onBack = {
                  navController.navigateUp()
                },
                onAfterLogin = { idUsuario ->
                    navController.navigate("lista/$idUsuario")
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Logado"
                        )
                    }
                },
                navController = navController
            )
        }
        composable("novoLogin"){
            NovoLogin(onAfterSave = {
                navController.navigateUp()
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Usuário Registrado"
                    )
                }
            },
                onBack = {
                    navController.navigateUp()
                }
            )
        }

        composable("lista/{idUsuario}",
            arguments = listOf(navArgument("idUsuario") { type = NavType.StringType })
        ) {
            val param = it.arguments?.getString("idUsuario")
            val idUsuario = param?.toInt()
            if (idUsuario != null) {
                ListaViagem(
                    idUsuario,
                    NovaViagem = { idUsuario ->
                        navController.navigate("novaViagem/$idUsuario")
                    },
                    listaDespesa = {idViagem ->
                        navController.navigate("listaDespesa/$idViagem")
                    },
                    navController
                )
            }
        }
        composable(
            "listaDespesa/{idDespesa}",
            arguments = listOf(navArgument("idDespesa") { type = NavType.StringType })
        ){
            val param = it.arguments?.getString("idDespesa")
            val idDespesa = param?.toInt()
            if (idDespesa != null) {
                ListaDespesas(
                    idDespesa,
                    novaDespesa = {idDespesa ->
                        navController.navigate("novaDespesa/$idDespesa")
                    }
                )
            }
        }


        composable("Sobre"
        ) {
            Sobre()
        }
        composable(
            "novaViagem/{idUsuario}",
            arguments = listOf(navArgument("idUsuario") { type = NavType.StringType })
        ) {
            val param = it.arguments?.getString("idUsuario")
            val idUsuario = param?.toInt()
            if (idUsuario != null) {
                NovaViagem(
                    idUsuario,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
        composable(
            "novaDespesa/{idViagem}",
            arguments = listOf(navArgument("idViagem") { type = NavType.StringType })
        ) {
            val param = it.arguments?.getString("idViagem")
            val idViagem = param?.toInt()
            if (idViagem != null) {
                NovaDespesa(
                    idViagem,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppViagensTheme {
        MyApp()
    }
}