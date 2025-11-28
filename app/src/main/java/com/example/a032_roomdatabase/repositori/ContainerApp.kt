package com.example.a032_roomdatabase.repositori

import android.app.Application
import android.content.Context
import com.example.a032_roomdatabase.room.DatabaseSiswa

// 1. Interface AppContainer
interface AppContainer {
    val repositoriSiswa: RepositoriSiswa
}

// 2. Class ContainerApp
class ContainerApp(private val context: Context) : AppContainer {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(DatabaseSiswa.getDatabase(context).siswaDao())
    }
}

// 3. Class AplikasiSiswa (Ditaruh di sini biar jadi satu file)
class AplikasiSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerApp(this)
    }
}