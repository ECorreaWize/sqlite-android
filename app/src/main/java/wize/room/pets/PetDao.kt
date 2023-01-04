package wize.room.pets

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao  {

    @Query("SELECT * FROM pets_table ORDER BY pet_name ASC")
    fun getAlphabetizedPets(): Flow<List<Pet>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pet: Pet)

    @Query("DELETE FROM pets_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteOnePet(pet: Pet)
}