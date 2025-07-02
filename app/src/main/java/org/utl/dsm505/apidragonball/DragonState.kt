package org.utl.dsm505.apidragonball


data class DragonState(
    val dragons: List<Dragon> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)