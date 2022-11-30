package dev.wxlf.companieslist.data.entities

import com.google.gson.annotations.SerializedName

data class CompanyEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String
)
