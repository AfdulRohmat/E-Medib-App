package com.example.e_medib.features.auth_feature.model.response

data class LoginModelResponse(
    val access_token: AccessToken,
    val data: LoginResponse,
    val meta: Meta
)