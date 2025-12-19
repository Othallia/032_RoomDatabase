package com.example.a032_roomdatabase.repositori

import com.example.a032_roomdatabase.room.Siswa
import com.example.a032_roomdatabase.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa : Siswa)

    // PERBAIKAN 1: Tambahkan tanda tanya (?) di sini
    fun getSiswaStream(id: Int): Flow<Siswa?>

    suspend fun deleteSiswa(siswa: Siswa)

    suspend fun updateSiswa(siswa: Siswa)
}

class OfflineRepositorisiswa(
    private val siswaDao: SiswaDao
): RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)

    // PERBAIKAN 2: Tambahkan tanda tanya (?) di sini juga
    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id)

    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa)
    override suspend fun updateSiswa(siswa: Siswa) = siswaDao.update(siswa)
}