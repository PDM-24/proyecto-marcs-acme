package com.project.smartgreen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Registro(
    val comentarios: String,
    val ubicacion: String,
    val tipoCultivo: String,
    val tipoSuelo: String,
    val fechaObservacion: String,
    val imagenUri: String? // Uri se almacena como String para facilitar la serialización
)

class ComentariosViewModel : ViewModel() {
    private val _registros = mutableStateListOf<Registro>()
    val registros: SnapshotStateList<Registro> = _registros

    fun addRegistro(registro: Registro) {
        _registros.add(registro)
    }
}
