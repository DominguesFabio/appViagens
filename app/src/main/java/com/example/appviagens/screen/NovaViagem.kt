package com.example.appviagens.screen

import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appviagens.entity.Usuario
import com.example.appviagens.viewModel.CriarNovaViagem
import com.example.appviagens.viewModel.NovaViagemViewMovelFactory
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

@Composable
fun NovaViagem (idUsuario: Int, onBack: () -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: CriarNovaViagem = viewModel(
        factory = NovaViagemViewMovelFactory(application)
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

    viewModel.classificacao = "T"

    val focusManager = LocalFocusManager.current

    val xAno: Int
    val xMes: Int
    val xDia: Int

    val xCalendario = Calendar.getInstance()

    // Fetching current year, month and day
    xAno = xCalendario.get(Calendar.YEAR)
    xMes = xCalendario.get(Calendar.MONTH)
    xDia = xCalendario.get(Calendar.DAY_OF_MONTH)

    xCalendario.time = Date()

    val calendarioDataIni = DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.dataIni = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, xAno, xMes, xDia
    )
    val calendarioDataFim = DatePickerDialog(
        ctx,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.dataFim = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, xAno, xMes, xDia
    )

    fun abrirCalendario(focused: Boolean, date: String) {
        if (focused && date == "Início") {
            calendarioDataIni.show();
        }
        if (focused && date == "Fim"){
            calendarioDataFim.show()
        }
    }

    Scaffold(scaffoldState = scaffoldState ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                value = viewModel.destino,
                onValueChange = { viewModel.destino = it},
                label = {
                    Text(text = "Destino")
                }
            )

            Row() {
                RadioButton(
                    selected = viewModel.classificacao == "L",
                    onClick = {viewModel.classificacao = "L"}
                )
                Text(
                    text = "Lazer",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Row() {
                RadioButton(
                    selected = viewModel.classificacao == "T",
                    onClick = {viewModel.classificacao = "T"}
                )
                Text(
                    text = "Trabalho",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            OutlinedTextField(
                value = viewModel.dataIni,
                onValueChange = {},
                label = {
                    Text(text = "Data de Início")
                },
                modifier = Modifier.onFocusChanged { a -> abrirCalendario(a.isFocused, "Início") }
            )
            OutlinedTextField(
                value = viewModel.dataFim,
                onValueChange = {},
                label = {
                    Text(text = "Data do fim")
                },
                modifier = Modifier.onFocusChanged { b -> abrirCalendario(b.isFocused, "Fim") }
            )
            Row() {
                Button(onClick = {
                    focusManager.clearFocus()
                    viewModel.criarNovaViagem(idUsuario, onSuccess = {
                        onBack()
                    })
                }) {
                    Text(text = "Nova viagem!")
                }
            }

        }
    }
}