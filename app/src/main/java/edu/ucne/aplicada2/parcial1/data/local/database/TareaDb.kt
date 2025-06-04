package edu.ucne.aplicada2.parcial1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.aplicada2.parcial1.data.local.dao.TareaDao
import edu.ucne.aplicada2.parcial1.data.local.entities.TareaEntity

@Database(
    entities = [
        TareaEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class TareaDb : RoomDatabase() {
    abstract fun TareaDao(): TareaDao
}
