package com.example.e_medib.network

import com.example.e_medib.features.aktivitas_feature.model.DataUbahDurasiAktivitasModel
import com.example.e_medib.features.aktivitas_feature.model.getAll.AktivitasResponse
import com.example.e_medib.features.aktivitas_feature.model.getAll.GetAllAktivitasResponse
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
import com.example.e_medib.features.pantau_kalori_feature.model.DataKonsumsiMakananModel
import com.example.e_medib.features.pantau_kalori_feature.model.getAll.GetAllKonsumsiMakananResponse
import com.example.e_medib.features.pantau_kalori_feature.model.getAll.KonsumsiMakananResponse
import com.example.e_medib.features.profile_feature.model.bmiModel.BMIResponse
import com.example.e_medib.features.profile_feature.model.bmiModel.DataBMIModel
import com.example.e_medib.features.profile_feature.model.bmiModel.GetAllBMIResponse
import com.example.e_medib.features.profile_feature.model.bmrModel.BMRResponse
import com.example.e_medib.features.profile_feature.model.bmrModel.DataBMRModel
import com.example.e_medib.features.profile_feature.model.bmrModel.GetAllBMRResponse
import com.example.e_medib.features.profile_feature.model.logoutModel.LogoutModelResponse
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface EMedibApi {
    // =========================== AUTH =========================
    // LOGIN API
    @POST("login")
    suspend fun doLogin(@Body login: LoginModel?): LoginModelResponse

    // LOGOUT
    @GET("logout")
    suspend fun doLogout(): LogoutModelResponse

    // =========================== HOMESCREEN =========================
    // GET DATA USER
    @GET("account")
    suspend fun getUserData(@HeaderMap headers: Map<String, String>): DataUserModelResponse

    // TEKANAN DARAH
    @POST("hitung-tekanan-darah")
    suspend fun hitungTekananDarah(
        @Body data: DataTekananDarahModel?,
        @HeaderMap headers: Map<String, String>
    ): HitungTekananDarahResponse

    @GET("tekanan-darah")
    suspend fun getAllTekananDarah(
        @Query("tanggal") tanggal: String,
        @HeaderMap headers: Map<String, String>
    ): GetAllTekananDarahResponse

    // KOLESTEROL
    @POST("hitung-kolsterol")
    suspend fun hitungKolesterol(
        @Body data: DataKolesterolModel,
        @HeaderMap headers: Map<String, String>
    ): HitungKolesterolResponse

    @GET("kolesterol")
    suspend fun getAllKolesterol(
        @Query("tanggal") tanggal: String,
        @HeaderMap headers: Map<String, String>
    ): GetAllKolesterolResponse

    // GULA DARAH
    @POST("hitung-gula-darah")
    suspend fun hitungGulaDarah(
        @Body data: DataGulaDarahModel,
        @HeaderMap headers: Map<String, String>
    ): HitungGulDarahResponse

    @GET("gula-darah")
    suspend fun getAllGulaDarah(
        @Query("tanggal") tanggal: String,
        @HeaderMap headers: Map<String, String>
    ): GetAllGulaDarahResponse

    // HBA1C
    @POST("hitung-hba1c")
    suspend fun hitungHba1c(
        @Body data: DataHba1cModel,
        @HeaderMap headers: Map<String, String>
    ): HitungHba1cResponse

    @GET("hba1c")
    suspend fun getAllHba1c(
        @Query("tanggal") tanggal: String,
        @HeaderMap headers: Map<String, String>
    ): GetAllHba1cResponse


    // =========================== AKTIVITAS =========================
    // GET DATA AKTIVITAS
    @GET("aktivitas")
    suspend fun getAllAktivitas(
        @HeaderMap headers: Map<String, String>,
        @Query("tingkat_aktivitas") tingkatAktivitas: String
    ): GetAllAktivitasResponse

    // UPDATE AKTIVITAS
    @PATCH("ubah-durasi-aktivitas/{id}")
    suspend fun ubahDurasiAktivitas(
        @Path("id") id: String,
        @HeaderMap headers: Map<String, String>,
        @Body data: DataUbahDurasiAktivitasModel
    ): AktivitasResponse

    // RESET AKTIVITAS
    @GET("reset")
    suspend fun resetAllAktivitas(
        @HeaderMap headers: Map<String, String>,
    )


    // =========================== PANTAU KALORI SCREEN =========================
    // GET ALL KONSUMSI MAKANAN
    @GET("konsumsi-makanan")
    suspend fun getAllKonsumsiMakanan(
        @Query("waktu_makan") waktuMakan: String,
        @Query("tanggal") tanggal: String,
        @HeaderMap headers: Map<String, String>
    ): GetAllKonsumsiMakananResponse


    // POST KONSUMSI MAKANAN
    @POST("tambah-konsumsi-makanan")
    suspend fun tambahKonsumsiMakanan(
        @Body data: DataKonsumsiMakananModel,
        @HeaderMap headers: Map<String, String>
    ): KonsumsiMakananResponse

    // =========================== PROFILE SCREEN =========================
    // POST BMI
    @POST("hitung-bmi")
    suspend fun hitungBMI(
        @Body data: DataBMIModel,
        @HeaderMap headers: Map<String, String>
    ): BMIResponse

    // GET ALL BMI
    @GET("bmi")
    suspend fun getAllBMI(
        @HeaderMap headers: Map<String, String>
    ): GetAllBMIResponse

    // POST BMR
    @POST("hitung-bmr")
    suspend fun hitungBMR(
        @Body data: DataBMRModel,
        @HeaderMap headers: Map<String, String>
    ): BMRResponse

    // GET ALL BMI
    @GET("bmr")
    suspend fun getAllBMR(
        @HeaderMap headers: Map<String, String>
    ): GetAllBMRResponse


}