package dev.wxlf.companieslist.presentation.models

data class DisplayableDetailsModel(
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)
