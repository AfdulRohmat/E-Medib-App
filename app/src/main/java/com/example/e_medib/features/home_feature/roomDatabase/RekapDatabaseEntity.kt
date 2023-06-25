package com.example.e_medib.features.home_feature.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "rekap_table")
data class RekapModelEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "gula_darah")
    val gulaDarah: String? = null,

    @ColumnInfo(name = "gula_darah_keterangan")
    val gulaDarahKeterangan: String? = null,

    @ColumnInfo(name = "kolesterol")
    val kolesterol: String? = null,

    @ColumnInfo(name = "kolesterolKeterangan")
    val kolesterolKeterangan: String? = null,

    @ColumnInfo(name = "gambar_luka")
    val gambarLuka: String? = null,

    @ColumnInfo(name = "catatan_luka")
    val catatanLuka: String? = null,

    @ColumnInfo(name = "total_konsumsi_kalori")
    val totalKonsumsiKalori: String? = null,

    @ColumnInfo(name = "total_pembakaran_kalori")
    val totalPembakaranKalori: String? = null,

    @ColumnInfo(name = "catatan")
    val catatan: String? = null,

    @ColumnInfo(name = "rekap_entry_date")
    val date: Date = Date.from(Instant.now())

)