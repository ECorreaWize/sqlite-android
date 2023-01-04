package wize.room.pets

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao) {

    val allPets: Flow<List<Pet>> = petDao.getAlphabetizedPets()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(pet: Pet) {
        petDao.insert(pet)
    }
}