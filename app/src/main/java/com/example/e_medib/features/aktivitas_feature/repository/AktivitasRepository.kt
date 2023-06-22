package com.example.e_medib.features.aktivitas_feature.repository

import com.example.e_medib.data.Resource
import com.example.e_medib.features.aktivitas_feature.model.DataUbahDurasiAktivitasModel
import com.example.e_medib.features.aktivitas_feature.model.getAll.AktivitasResponse
import com.example.e_medib.features.aktivitas_feature.model.getAll.GetAllAktivitasResponse
import com.example.e_medib.features.home_feature.model.gulaDarah.DataGulaDarahModel
import com.example.e_medib.features.home_feature.model.gulaDarah.hitung.HitungGulDarahResponse
import com.example.e_medib.network.EMedibApi
import javax.inject.Inject

class AktivitasRepository @Inject constructor(private val eMedibApi: EMedibApi) {

    // GET ALL AKTIVITAS
    suspend fun getAllAktivitas(
        headers: Map<String, String>,
        tingkatAktivitas: String
    ): Resource<GetAllAktivitasResponse> {
        Resource.Loading(data = true)
        return try {
            val dataAllAktivitas = eMedibApi.getAllAktivitas(headers, tingkatAktivitas)
            Resource.Success(data = dataAllAktivitas)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // UPDATE DURASI
    suspend fun ubahDurasiAktivitas(
        id: String,
        data: DataUbahDurasiAktivitasModel,
        headers: Map<String, String>
    ): Resource<AktivitasResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.ubahDurasiAktivitas(id, headers, data)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // RESET
    suspend fun resetAllAktivitas(
        headers: Map<String, String>,
    ): Resource<Any> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.resetAllAktivitas(headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

}