package com.example.a032_roomdatabase.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a032_roomdatabase.repositori.RepositoriSiswa
import com.example.a032_roomdatabase.view.route.DestinasiDetailSiswa // Pastikan import route ini sudah benar (sesuai perbaikan sebelumnya)
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch // Tambahkan import ini untuk viewModelScope.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    val uiDetailState: StateFlow<DetailSiswaUiState> =
        repositoriSiswa.getSiswaStream(idSiswa)
            .filterNotNull()
            .map {
                DetailSiswaUiState(detailSiswa = it.toDetailSiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = DetailSiswaUiState()
            )

    // Perbaikan: Pastikan menggunakan viewModelScope.launch
    fun deleteSiswa() {
        viewModelScope.launch {
            repositoriSiswa.deleteSiswa(uiDetailState.value.detailSiswa.toSiswa())
        }
    }
}

data class DetailSiswaUiState(
    val detailSiswa: DetailSiswa = DetailSiswa()
)