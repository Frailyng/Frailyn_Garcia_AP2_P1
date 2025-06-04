package edu.ucne.aplicada2.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.aplicada2.parcial1.data.local.database.TareaDb
import edu.ucne.aplicada2.parcial1.presentation.navigation.HostNavigation
import edu.ucne.aplicada2.parcial1.ui.theme.Parcial1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var tareaDb: TareaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        tareaDb = Room.databaseBuilder(
            applicationContext,
            TareaDb::class.java,
            "Tarea.db"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            Parcial1Theme {
                val nav = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues))
                    HostNavigation(
                        navHostController = nav
                    )
                }
            }
        }
    }
}
