package com.example.e_medib.features.auth_feature.repository

import android.util.Log
import com.example.e_medib.data.Resource
import com.example.e_medib.features.auth_feature.model.LoginModel
import com.example.e_medib.features.auth_feature.model.response.LoginModelResponse
import com.example.e_medib.network.EMedibApi
import javax.inject.Inject

class AuthRepository @Inject constructor(private val eMedibApi: EMedibApi) {

    // LOGIN
    suspend fun doLogin(data: LoginModel): Resource<LoginModelResponse> {
        Resource.Loading(data = true)
        return try {
            val loginResult = eMedibApi.doLogin(data)
            Resource.Success(data = loginResult)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        } finally {
            Resource.Loading(data = false)
        }
    }

}