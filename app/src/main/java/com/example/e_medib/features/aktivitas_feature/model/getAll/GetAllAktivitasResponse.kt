package com.example.e_medib.features.aktivitas_feature.model.getAll

data class GetAllAktivitasResponse(
    val `data`: List<AktivitasResponse>,
    val total_kalori: Int? = null,
    val total_menit: Int? = null
)