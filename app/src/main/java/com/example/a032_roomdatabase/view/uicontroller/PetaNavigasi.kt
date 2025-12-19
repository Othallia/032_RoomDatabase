package com.example.a032_roomdatabase.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// --- BAGIAN IMPORT YANG DIPERBAIKI ---

// 1. Import Halaman/Screen (Pastikan nama class-nya sesuai dengan file HalamanHome.kt dan HalamanEntry.kt)
import com.example.a032_roomdatabase.view.DetailSiswaScreen
import com.example.a032_roomdatabase.view.EditSiswaScreen

// 2. Import Route (Diubah menjadi 'Route' huruf Besar semua sesuai nama Folder)
import com.example.a032_roomdatabase.view.route.DestinasiDetailSiswa
import com.example.a032_roomdatabase.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.a032_roomdatabase.view.route.DestinasiEditSiswa
import com.example.a032_roomdatabase.view.route.DestinasiEntry
import com.example.a032_roomdatabase.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHome.route, modifier = Modifier)
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DestinasiDetailSiswa.route}/$it")
                },
                navigateToItemUpdate = {
                    // Nanti bisa diisi logika navigasi ke update jika diperlukan
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        // edit 2 : tambahkan 2 composable route
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateToEditItem = { navController.navigate("${DestinasiEditSiswa.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = DestinasiEditSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}