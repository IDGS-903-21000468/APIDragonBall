package org.utl.dsm505.apidragonball


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DragonViewModel : ViewModel() {
    var state by mutableStateOf(DragonState())
        private set

    init {
        fetchDragons()
    }

    fun fetchDragons() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            try {
                val response = ApiService.getInstance().getCharacters()
                state = state.copy(
                    dragons = response.items,
                    isLoading = false
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = "Error al cargar personajes: ${e.message}"
                )
                e.printStackTrace()
            }
        }
    }
}