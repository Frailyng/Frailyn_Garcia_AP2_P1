package edu.ucne.aplicada2.parcial1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object TareaList: Screen()
    @Serializable
    data class TareaEntity(val tareaId: Int?) : Screen()
    @Serializable
    data object Home : Screen()
}
