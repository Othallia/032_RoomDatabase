package com.example.a032_roomdatabase.view.Route

import com.example.a032_roomdatabase.R

object DestinasiDetailSiswa  : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"

}