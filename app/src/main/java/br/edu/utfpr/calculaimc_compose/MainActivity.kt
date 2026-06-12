package br.edu.utfpr.calculaimc_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.utfpr.calculaimc_compose.model.ImcViewModel
import br.edu.utfpr.calculaimc_compose.ui.theme.CalculaIMCComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaIMCComposeTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home" ) {

                    composable( "home",
                        enterTransition = {
                            slideInHorizontally()
                        },
                        exitTransition = {
                            slideOutHorizontally()
                        }
                    ) {
                        CalculaImcScreen(
                            onNavegateToDeveloper = {
                                navController.navigate("developer")
                            }
                        )
                    }

                    composable( "developer") {
                        DeveloperScreen()
                    }


                }




            }
        }
    }
}

@Composable
fun CalculaImcScreen(
    viewModel: ImcViewModel = viewModel(),
    onNavegateToDeveloper: () -> Unit
) {

    var peso = viewModel.peso
    var altura = viewModel.altura
    var resultado = viewModel.resultado

    var focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        OutlinedTextField(
            value = viewModel.peso,
            onValueChange = { viewModel.onPesoChange(it) },
            label = { Text("Peso em Kg") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = viewModel.altura,
            onValueChange = { viewModel.onAlturaChange(it) },
            label = { Text("Altura em m") },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if ( resultado.toDouble() > 0 ) {
            PanelResult( resultado )
        }

        PanelButtons(
            onCalcularImcClick = { viewModel.calculaImc() },
            onLimparClick = {
                viewModel.limparTela()
                focusRequester.requestFocus()
            }
        )

        Button(
            onClick = onNavegateToDeveloper,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text( "Sobre o Desenvolvedor" )
        }


    }
} //fim do CalculaImcScreen

@Composable
fun PanelResult(resultado:String) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Resultado:"
        )

        Text(
            text = resultado,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun PanelButtons(
    onCalcularImcClick: () -> Unit,
    onLimparClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Button(
            onClick = onCalcularImcClick,
            modifier = Modifier
                .weight(1f)
        ) {
            Text("Calcular")
        }

        Button(
            onClick = onLimparClick,
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary )
        ) {
            Text( "Limpar" )
        }
    }

}

@Composable
fun DeveloperScreen( modifier: Modifier = Modifier ) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Desenvolvido por:"
        )

        Text(
            text = "PM45S-2026-1",
            style = MaterialTheme.typography.headlineLarge
        )
    }

}




@Preview(showBackground = true)
@Composable
fun PanelButtonsPreview() {
    CalculaIMCComposeTheme {
        PanelButtons(
            {},
            {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PanelResultPreview() {
    CalculaIMCComposeTheme {
        PanelResult( "27.56" )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculaImcScreenPreview() {
    CalculaIMCComposeTheme {
        CalculaImcScreen(
            onNavegateToDeveloper = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeveloperSreenPreview() {
    CalculaIMCComposeTheme {
        DeveloperScreen()
    }
}
