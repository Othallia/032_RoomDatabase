package com.example.a032_roomdatabase.view.route

import com.example.a032_roomdatabase.R
import com.example.a032_roomdatabase.view.Route.DestinasiNavigasi

object DestinasiEditSiswa : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}
