package com.example.dragdropcolumncompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<List<Section>>(listOf())
    val uiState = _uiState.asStateFlow()

    private val sections = listOf(
        Section(name ="Section 1"),
        Section(name ="Section 2"),
        Section(name ="Section 3"),
        Section(name ="Section 4"),
        Section(name ="Section 5"),
        Section(name ="Section 6"),
    )

    fun swapSections(from: Int, to: Int) {
        val fromItem = _uiState.value[from]
        val toItem = _uiState.value[to]
        val newList = _uiState.value.toMutableList()
        newList[from] = toItem
        newList[to] = fromItem

        _uiState.value = newList
    }

    fun sectionClicked(item: Section) {
        println("Clicked $item")
    }

    init {
        _uiState.value = sections.shuffled()
    }

}

data class Section(
    val id: Int = internalId++,
    val name: String = "",
    val description: String = "",
    val color: Long = Random(id).nextLong()
) {
    companion object {
        private var internalId = 0
    }
}