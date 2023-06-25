package com.example.e_medib.features.home_feature.repository

import android.util.Log
import com.example.e_medib.data.Resource
import com.example.e_medib.features.home_feature.model.catatan.DataCatatanModel
import com.example.e_medib.features.home_feature.model.catatan.response.CatatanResponse
import com.example.e_medib.features.home_feature.model.catatan.response.GetAllCatatanRespone
import com.example.e_medib.features.home_feature.model.diary.DataDiaryModel
import com.example.e_medib.features.home_feature.model.diary.response.DiaryResponse
import com.example.e_medib.features.home_feature.model.diary.response.GetAllDiaryResponse
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
import com.example.e_medib.features.home_feature.roomDatabase.RekapDatabaseDao
import com.example.e_medib.features.home_feature.roomDatabase.RekapModelEntity
import com.example.e_medib.network.EMedibApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val eMedibApi: EMedibApi,
    private val rekapDatabaseDao: RekapDatabaseDao
) {

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
        tanggal: String, headers: Map<String, String>
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
        data: DataHba1cModel, headers: Map<String, String>
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
        tanggal: String, headers: Map<String, String>
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
        data: DataGulaDarahModel, headers: Map<String, String>
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
        tanggal: String, headers: Map<String, String>
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
        data: DataKolesterolModel, headers: Map<String, String>
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
        tanggal: String, headers: Map<String, String>
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
        data: DataTekananDarahModel, headers: Map<String, String>
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

    // ============ CATATAN
    suspend fun getAllCatatan(
        headers: Map<String, String>,
        tanggal: String,
    ): Resource<GetAllCatatanRespone> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.getAllCatatan(headers, tanggal)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    suspend fun tambahCatatan(
        data: DataCatatanModel, headers: Map<String, String>
    ): Resource<CatatanResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.tambahCatatan(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Log.d("Error", "${e}")
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

    // ============== DIARY REKAP ROOM
    // create Rekap Room
    suspend fun addRekapRoom(rekapModelEntity: RekapModelEntity) =
        rekapDatabaseDao.insertRekap(rekapModelEntity)

    // get Rekap All
    fun getAllRekapRoom(): Flow<List<RekapModelEntity>> =
        rekapDatabaseDao.getAllRekap()

    // get Rekap by id
    suspend fun getRekapRoomById(id: String) = rekapDatabaseDao.getRekapById(id)

    //update Rekap
    suspend fun updateRekapRoom(rekapModelEntity: RekapModelEntity) =
        rekapDatabaseDao.updateRekap(rekapModelEntity)

    // DIARY REKAP API
    suspend fun tambahDiaryRekap(
        data: DataDiaryModel, headers: Map<String, String>
    ): Resource<DiaryResponse> {
        Resource.Loading(data = true)
        return try {
            val response = eMedibApi.tambahDiaryRekap(data, headers)
            Resource.Success(data = response)
        } catch (e: Exception) {
            Log.d("Error", "${e}")
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }
}