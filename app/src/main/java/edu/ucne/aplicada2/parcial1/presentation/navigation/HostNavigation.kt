package edu.ucne.aplicada2.parcial1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.aplicada2.parcial1.presentation.Home.HomeScreen
import edu.ucne.aplicada2.parcial1.presentation.tareas.TareaListScreen
import edu.ucne.aplicada2.parcial1.presentation.tareas.TareaScreen


@Composable
fun HostNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home
    ) {
        //inicio
        composable <Screen.Home> {
            HomeScreen(navController = navHostController)
        }

        composable<Screen.TareaList>{

            TareaListScreen(
                goToTarea = { id ->
                    navHostController.navigate(Screen.TareaEntity(id ?: 0))
                },
                createTarea = {
                    navHostController.navigate((Screen.TareaEntity(0)))
                }
            )
        }

        composable <Screen.TareaEntity>{ backStack ->
            val tareaId = backStack.toRoute<Screen.TareaEntity>().tareaId
            TareaScreen(
                tareaId = tareaId,
                goBack = { navHostController.popBackStack() }
            )
        }
    }
}


