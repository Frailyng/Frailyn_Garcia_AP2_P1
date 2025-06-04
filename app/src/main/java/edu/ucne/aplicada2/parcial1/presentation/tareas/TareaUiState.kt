package edu.ucne.aplicada2.parcial1.presentation.tareas

import edu.ucne.aplicada2.parcial1.data.local.entities.TareaEntity

data class TareaUiState(
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0,
    val errorMessage: String? = null,
    val tareas: List<TareaEntity> = emptyList()
)
