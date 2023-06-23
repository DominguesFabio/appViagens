package com.example.appviagens.screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appviagens.viewModel.ListaViagensViewModel
import com.example.appviagens.viewModel.ListaViagensViewModelFactory

@Composable
fun ListaViagem (idUsuario: Int, NovaViagem: (Int) -> Unit, listaDespesa:(Int) -> Unit){

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListaViagensViewModel = viewModel(
        factory = ListaViagensViewModelFactory (application)
    )

    viewModel.loadAllViagens(idUsuario)

    Column(Modifier.fillMaxSize()) {
        Button(
            onClick = {
                NovaViagem(idUsuario)
            }) {
            Text(text = "Nova Viagem?!")
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

    }
}