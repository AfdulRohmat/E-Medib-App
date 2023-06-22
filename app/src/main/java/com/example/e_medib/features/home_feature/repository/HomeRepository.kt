package com.example.e_medib.features.home_feature.repository

import com.example.e_medib.data.Resource
import com.example.e_medib.features.auth_feature.model.LoginModel
import com.example.e_medib.features.auth_feature.model.response.LoginModelResponse
import com.example.e_medib.features.home_feature.model.gulaDarah.DataGulaDarahModel
import com.example.e_medib.features.home_feature.model.gulaDarah.getAll.GetAllGulaDarahResponse
import com.example.e_medib.features.home_feature.model.gulaDarah.hitung.HitungGulDarahResponse
import com.example.e_medib.features.home_feature.model.hba1c.DataHba1cModel
import com.example.e_medib.features.home_feature.model.hba1c.getAll.GetAllHba1cResponse
import com.example.e_medib.features.home_feature.model.hba1c.hitung.HitungHba1cResponse
import com.example.e_medib.features.home_feature.model.kolesterol.DataKolesterolModel
import com.example.e_medib.features.home_feature.model.kolesterol.getAll.GetAllKolesterolResponse
import com.example.e_medib.features.home_feature.model.kolesterol.hitung.HitungKolesterolResponse
import com.example.e_medib.features.home_feature.model.tekananDarah.DataTekananDarahModel
import com.example.e_medib.features.home_feature.model.tekananDarah.getAll.GetAllTekananDarahResponse
import com.example.e_medib.features.home_feature.model.tekananDarah.hitung.HitungTekananDarahResponse
import com.example.e_medib.features.home_feature.model.userData.DataUserModelResponse
import com.example.e_medib.network.EMedibApi
import javax.inject.Inject

class HomeRepository @Inject constructor(private val eMedibApi: EMedibApi) {

    // GET DATA USER
    suspend fun getDataUser(headers: Map<String, String>): Resource<DataUserModelResponse> {
        Resource.Loading(data = true)
        return try {
            val dataUser = eMedibApi.getUserData(headers)
            Resource.Success(data = dataUser)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // HBA1C
    suspend fun getAllHba1c(
        tanggal: String,
        headers: Map<String, String>
    ): Resource<GetAllHba1cResponse> {
        Resource.Loading(data = true)
        return try {
            val dataAllHba1c = eMedibApi.getAllHba1c(tanggal, headers)
            Resource.Success(data = dataAllHba1c)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    suspend fun hitungHba1c(
        data: DataHba1cModel,
        headers: Map<String, String>
    ): Resource<HitungHba1cResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.hitungHba1c(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }


    // GULA DARAH
    suspend fun getAllGulaDarah(
        tanggal: String,
        headers: Map<String, String>
    ): Resource<GetAllGulaDarahResponse> {
        Resource.Loading(data = true)
        return try {
            val dataAllGulaDarah = eMedibApi.getAllGulaDarah(tanggal, headers)
            Resource.Success(data = dataAllGulaDarah)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    suspend fun hitungGulaDarah(
        data: DataGulaDarahModel,
        headers: Map<String, String>
    ): Resource<HitungGulDarahResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.hitungGulaDarah(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // KOLESTEROL
    suspend fun getAllKolesterol(
        tanggal: String,
        headers: Map<String, String>
    ): Resource<GetAllKolesterolResponse> {
        Resource.Loading(data = true)
        return try {
            val dataAllKolesterol = eMedibApi.getAllKolesterol(tanggal, headers)
            Resource.Success(data = dataAllKolesterol)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    suspend fun hitungKolestertol(
        data: DataKolesterolModel,
        headers: Map<String, String>
    ): Resource<HitungKolesterolResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.hitungKolesterol(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // TEKANAN DARAH
    suspend fun getAllTekananDarah(
        tanggal: String,
        headers: Map<String, String>
    ): Resource<GetAllTekananDarahResponse> {
        Resource.Loading(data = true)
        return try {
            val dataAllTekananDarah = eMedibApi.getAllTekananDarah(tanggal, headers)
            Resource.Success(data = dataAllTekananDarah)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    suspend fun hitungTekananDarah(
        data: DataTekananDarahModel,
        headers: Map<String, String>
    ): Resource<HitungTekananDarahResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.hitungTekananDarah(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }
}