package com.project.smartgreen.data.model

data class Crop(
    val id: String? = null,
    val name: String,
    val plantedDate: String,
    val location: String,
    val soilType: String,
    val cropType: String,
    val notes: String?,
    val user: String? = null
)