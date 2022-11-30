package dev.wxlf.companieslist.data.entities

import com.google.gson.annotations.SerializedName

data class CompanyDetailsEntity(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("img") var img: String,
    @SerializedName("description") var description: String,
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lon: Double,
    @SerializedName("www") var www: String,
    @SerializedName("phone") var phone: String

)
