package com.example.e_medib.features.aktivitas_feature.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_medib.data.Resource
import com.example.e_medib.features.aktivitas_feature.model.DataUbahDurasiAktivitasModel
import com.example.e_medib.features.aktivitas_feature.model.getAll.GetAllAktivitasResponse
import com.example.e_medib.features.aktivitas_feature.repository.AktivitasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AktivitasViewModel @Inject constructor(
    private val aktivitasRepository: AktivitasRepository
) : ViewModel() {
    var isAktivitasLoading: Boolean by mutableStateOf(false)
    var dataAllAktivitas: GetAllAktivitasResponse? by mutableStateOf(
        GetAllAktivitasResponse(data = emptyList())
    )

    fun getAllAktivitas(headers: Map<String, String>, tingkatAktivitas: String) {
        viewModelScope.launch {
            isAktivitasLoading = true

            try {
                when (val response =
                    aktivitasRepository.getAllAktivitas(headers, tingkatAktivitas)) {
                    is Resource.Success -> {
                        dataAllAktivitas = response.data!!
                    }
                    is Resource.Error -> {
                        Log.d("dataAllAktivitas", "${response.message}")
                    }
                    else -> {
                        Log.d("dataAllAktivitas", "$response")
                    }
                }
            } catch (e: Exception) {
                Log.d("dataAllAktivitas", "$e")

            } finally {
                isAktivitasLoading = false
            }
        }
    }

    fun ubahDurasiAktivitas(
        id: String,
        data: DataUbahDurasiAktivitasModel,
        headers: Map<String, String>,
        tingkatAktivitas: String,
        context: Context
    ) {
        viewModelScope.launch {
            isAktivitasLoading = true

            try {
                when (val response =
                    aktivitasRepository.ubahDurasiAktivitas(id, data, headers)) {
                    is Resource.Success -> {
                        Toast.makeText(
                            context, "Berhasil menambah durasi",
                            Toast.LENGTH_SHORT
                        ).show()
                        getAllAktivitas(headers, tingkatAktivitas)
                    }
                    is Resource.Error -> {
                        Log.d("updateAktivitas", "${response.data}")
                    }
                    else -> {
                        Log.d("updateAktivitas", "$response")
                    }
                }
            } catch (e: Exception) {
                Log.d("updateAktivitas", "$e")

            } finally {
                isAktivitasLoading = false
            }
        }
    }

    fun resetAllAktivitas(
        headers: Map<String, String>,
        tingkatAktivitas: String,
        context: Context
    ) {
        viewModelScope.launch {
            isAktivitasLoading = true

            try {
                when (val response =
                    aktivitasRepository.resetAllAktivitas(headers)) {
                    is Resource.Success -> {
                        Toast.makeText(
                            context, "Berhasil mereset data aktivitas",
                            Toast.LENGTH_SHORT
                        ).show()
                        getAllAktivitas(headers, tingkatAktivitas)
                    }
                    is Resource.Error -> {
                        Log.d("mereset", "${response.message}")
                    }
                    else -> {
                        Log.d("mereset", "$response")
                    }
                }
            } catch (e: Exception) {
                Log.d("mereset", "$e")

            } finally {
                isAktivitasLoading = false
            }
        }
    }
}