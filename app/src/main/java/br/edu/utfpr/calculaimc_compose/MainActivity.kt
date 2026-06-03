package br.edu.utfpr.calculaimc_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.calculaimc_compose.ui.theme.CalculaIMCComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaIMCComposeTheme {
                    CalculaImcScreen(
                        name = "Android",
                        modifier = Modifier.padding( top = 40.dp)
                    )
            }
        }
    }
}

@Composable
fun CalculaImcScreen(name: String, modifier: Modifier = Modifier) {

    var peso by rememberSaveable { mutableStateOf( "") }
    var altura by rememberSaveable { mutableStateOf( "") }

    var resultado by rememberSaveable { mutableStateOf( "0.00") }

    val calculaImc = {
        val pesoDouble = peso.toDoubleOrNull()
        val alturaDouble = altura.toDoubleOrNull()

        if ( pesoDouble != null && alturaDouble != null ) {
            val imc = pesoDouble / (alturaDouble * alturaDouble)
            resultado = "%.2f".format(imc)
        }

    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp)
    )
    {

        OutlinedTextField(
            value = peso,
            onValueChange = {peso = it},
            label = { Text("Peso em Kg") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = altura,
            onValueChange = {altura=it},
            label = { Text("Altura em m") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Text (
            text = "Resultado:",
            modifier = Modifier
                .padding(16.dp)
        )

        Text (
            text = resultado,
            modifier = Modifier
                .padding(16.dp)
        )

        Row {
            Button(
                onClick = calculaImc,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text("Calcular")
            }

            Button(
                onClick = {peso=""; altura=""; resultado="0.00"},
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text( "Limpar" )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculaImcScreenPreview() {
    CalculaIMCComposeTheme {
        CalculaImcScreen("Android")
    }
}