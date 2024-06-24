package com.project.smartgreen.ui.viewmodel

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.project.smartgreen.getLastLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
    private val _ubicacion = MutableStateFlow<Location?>(null)
    val ubicacion = _ubicacion.asStateFlow()
    val registros: SnapshotStateList<Registro> = _registros

    fun addRegistro(registro: Registro) {
        _registros.add(registro)
    }

    fun getLocation(context: Context, permission: Boolean) {
        Log.d("Permission", permission.toString())
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        viewModelScope.launch {
            _ubicacion.value = getLastLocation(fusedLocationClient)
        }
    }
}