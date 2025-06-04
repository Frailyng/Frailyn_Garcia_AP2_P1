package edu.ucne.aplicada2.parcial1.presentation.tareas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.aplicada2.parcial1.data.local.entities.TareaEntity

@Composable
fun TareaListScreen(
    viewModel: TareasViewModel = hiltViewModel(),
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit,
    deleteTarea: ((TareaEntity) -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TareaListBodyScreen(
        uiState = uiState,
        goToTarea = goToTarea,
        createTarea = createTarea,
        deleteTarea = { tareas ->
            viewModel.onEvent(TareaEvent.TareaChange(tareas.tareaId ?: 0))
            viewModel.onEvent(TareaEvent.Delete)
        }
    )
}


@Composable
private fun TareaRow(
    it: TareaEntity,
    goToTarea: () -> Unit,
    deleteTarea:(TareaEntity) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(modifier = Modifier.weight(1f), text = it.tareaId.toString(), color = Color.Black)
        Text(
            modifier = Modifier.weight(2f),
            text = it.descripcion,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(modifier = Modifier.weight(2f), text = it.tiempo.toString(), color = Color.Black)
        IconButton(onClick = goToTarea) {
            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
        }
        IconButton(onClick = {deleteTarea(it)}) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
        }

    }
    HorizontalDivider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaListBodyScreen(
    uiState: TareaUiState,
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit,
    deleteTarea: (TareaEntity) -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Lista de Tareas") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createTarea
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create a new Ticket"
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.tareas) { tarea ->
                    TareaRow(
                        it = tarea,
                        goToTarea = { goToTarea(tarea.tareaId ?: 0) },
                        deleteTarea = deleteTarea
                    )
                }
            }
        }
    }
}
