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
import com.example.appviagens.viewModel.ListaDespesaViewModel
import com.example.appviagens.viewModel.ListaDespesaViewModelFactory

@Composable
fun ListaDespesas (despesaId: Int, novaDespesa: (Int) -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: ListaDespesaViewModel = viewModel(
        factory = ListaDespesaViewModelFactory(application)
    )


    viewModel.loadAllDespesa(despesaId)
    var total: Float = 0.00f
    for(despesa in viewModel.despesa.value) {
        total = despesa.despesa + total
    }

    Column(Modifier.fillMaxSize()) {
        Button(
            onClick = {
                novaDespesa(despesaId)
            }) {
            Text(text = "Add a new expense")
        }
        LazyColumn() {
            items(items = viewModel.despesa.value) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clickable { }
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "${it.nome}",
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "${it.despesa}",
                        )

                    }
                }
            }
        }
        Text(
            text = "Total: " + total
        )

    }
}