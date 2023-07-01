package com.example.appviagens.screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appviagens.ui.theme.PurpleGrey80
import com.example.appviagens.viewModel.ListaViagensViewModel
import com.example.appviagens.viewModel.ListaViagensViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ListaViagem (idUsuario: Int, NovaViagem: (Int) -> Unit, listaDespesa:(Int) -> Unit, navController: NavController){

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListaViagensViewModel = viewModel(
        factory = ListaViagensViewModelFactory (application)
    )

    viewModel.loadAllViagens(idUsuario)

    Column(Modifier.fillMaxSize()) {
        BottomAppBar(){
            BottomNavigation() {
                BottomNavigationItem(
                    selected = true,
                    onClick = {
                        navController.navigate("Viagens")
                    },
                    label = {
                        Text(text = "Despesas")
                    },
                    icon = { Icon(Icons.Filled.List, contentDescription = "")
                    })
                BottomNavigationItem(
                    selected = false,
                    onClick = {  navController.navigate("") },
                    label = {
                        Text(text = "Viagens")
                    },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "")
                    })
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        navController.navigate("Sobre")   },
                    label = {
                        Text(text = "Sobre")
                    },
                    icon = { Icon(Icons.Filled.Info, contentDescription = "")
                    }
                )
            }
        }
        LazyColumn() {
            items(items = viewModel.viagens.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row( modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.destino}",
                        )
                        Spacer(Modifier.weight(1f))

                        Button(
                            onClick = {
                                listaDespesa(it.id)
                            }) {
                            Text(text = "Adicionar despesa")
                        }

                    }

                }
            }
        }
        Button(
            onClick = {
                NovaViagem(idUsuario)
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Nova Viagem?!")

        }
    }

}