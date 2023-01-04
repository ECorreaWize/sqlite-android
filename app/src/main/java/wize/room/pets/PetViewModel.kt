package wize.room.pets

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository): ViewModel() {

    val allPets: LiveData<List<Pet>> = repository.allPets.asLiveData()

    fun insert(pet: Pet) = viewModelScope.launch {
        repository.insert(pet)
    }
}

class PetViewModelFactory(private val repository: PetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }
}