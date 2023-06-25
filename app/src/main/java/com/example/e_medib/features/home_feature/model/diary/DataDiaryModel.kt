package com.example.e_medib.features.home_feature.model.diary

data class DataDiaryModel(
    val catatan: String,
    val catatan_luka: String,
    val gambar_luka: Any? = null,
    val gula_darah: String,
    val gula_darah_keterangan: String,
    val kolesterol: String,
    val kolesterol_keterangan: String,
    val total_konsumsi_kalori: String,
    val total_pembakaran_kalori: String
)