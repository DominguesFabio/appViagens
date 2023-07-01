package com.example.appviagens.screen

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun appBar ( navController: NavHostController, scaffoldState: ScaffoldState, idUsuario : Int){
    /*Scaffold(
        topBar = {
            BottomAppBar() {
                BottomNavigation() {
                    BottomNavigationItem(
                        selected = true,
                        onClick = {
                            navController.navigate("Despesas")
                        },
                        label = {
                            Text(text = "Despesas")
                        },
                        icon = {
                            Icon(Icons.Filled.List, contentDescription = "")
                        })
                    BottomNavigationItem(
                        selected = false,
                        onClick = { navController.navigate("Viagens") },
                        label = {
                            Text(text = "Viagens")
                        },
                        icon = {
                            Icon(Icons.Filled.Home, contentDescription = "")
                        })
                    BottomNavigationItem(
                        selected = false,
                        onClick = {
                            navController.navigate("Sobre")
                        },
                        label = {
                            Text(text = "Sobre")
                        },
                        icon = {
                            Icon(Icons.Filled.Info, contentDescription = "")
                        }
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it)) {
            NavHost(navController = navController,startDestination = "Sobre"){

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
                                navController.navigate("list_expense/$idViagem")
                            },
                            navController
                        )
                    }
                }
                composable(
                    "Viagens",
                    arguments = listOf(navArgument("idUsuario") { type = NavType.StringType })
                ) {
                    Sobre()
                }
                composable(
                    "Despesas",
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
            }
        }
    }*/
}