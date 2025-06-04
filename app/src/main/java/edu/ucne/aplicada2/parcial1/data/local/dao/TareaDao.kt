package edu.ucne.aplicada2.parcial1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.aplicada2.parcial1.data.local.entities.TareaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Upsert
    suspend fun save(tareaEntity: TareaEntity)

    @Query(
        """
            SELECT *
            FROM Tareas
            WHERE tareaId=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): TareaEntity?

    @Delete
    suspend fun delete(tareas: TareaEntity)

    @Query("SELECT * FROM Tareas")
    fun getAll(): Flow<List<TareaEntity>>
}
