package com.project.smartgreen.data.model

data class Comment(
    val comment: String,
    val location: String,
    val cropType: String,
    val soilType: String,
    val observationDateTime: String
)