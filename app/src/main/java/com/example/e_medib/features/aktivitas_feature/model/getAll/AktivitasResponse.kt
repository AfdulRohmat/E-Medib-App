package com.example.e_medib.features.aktivitas_feature.model.getAll

data class AktivitasResponse(
    val created_at: Any,
    val deleted_at: Any,
    val durasi: String,
    val id: Int,
    val kalori: String,
    val met: String,
    val nama_aktivitas: String,
    val tingkat_aktivitas: String,
    val updated_at: String,
    val user_id: Int
)