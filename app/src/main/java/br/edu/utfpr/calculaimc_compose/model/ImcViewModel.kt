package br.edu.utfpr.calculaimc_compose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImcViewModel : ViewModel() {
    var peso by mutableStateOf( "")
        private set

    var altura by mutableStateOf( "")
        private set

    var resultado by mutableStateOf( "0.00")

    fun onPesoChange(novoPeso: String) {
        peso = novoPeso
    }

    fun onAlturaChange(novaAltura: String) {
        altura = novaAltura
    }

    fun calculaImc() {
        val pesoDouble = peso.toDoubleOrNull()
        val alturaDouble = altura.toDoubleOrNull()

        if ( pesoDouble != null && alturaDouble != null ) {
            val imc = pesoDouble / (alturaDouble * alturaDouble)
            resultado = "%.2f".format(imc)
        }
    }

    fun limparTela() {
        peso = ""
        altura = ""
        resultado = "0.00"
    }

}