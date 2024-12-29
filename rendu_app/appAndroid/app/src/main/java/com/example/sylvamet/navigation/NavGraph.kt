package com.example.sylvamet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sylvamet.ui.theme.Cubages
import com.example.sylvamet.ui.theme.CubageEdit
import com.example.sylvamet.ui.theme.Saisir

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "cubages"
    ) {
        composable("cubages") {
            Cubages(navController)
        }
        composable("cubageEdit") {
            CubageEdit(navController)
        }
        composable("saisir") {
            Saisir(navController)
        }
    }
}