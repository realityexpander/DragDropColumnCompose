package com.example.dragdropcolumncompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<List<Stuff>>(listOf())
    val uiState = _uiState.asStateFlow()

    private val stuff = listOf(
        Stuff(name ="Stuff 1"),
        Stuff(name ="Stuff 2"),
        Stuff(name ="Stuff 3"),
        Stuff(name ="Stuff 4"),
        Stuff(name ="Stuff 5"),
        Stuff(name ="Stuff 6"),
    )

    fun swapSections(from: Int, to: Int) {
        val fromItem = _uiState.value[from]
        val toItem = _uiState.value[to]
        val newList = _uiState.value.toMutableList()
        newList[from] = toItem
        newList[to] = fromItem

        _uiState.value = newList
    }

    init {
        _uiState.value = stuff.shuffled()
    }

}

data class Stuff(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    var share: Int = 0,
    var trend: Int = 0,
    val color: Long = Random(id).nextLong()
)