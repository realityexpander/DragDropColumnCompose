package com.example.dragdropcolumncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dragdropcolumncompose.ui.theme.DragDropColumnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragDropColumnComposeTheme {
                val viewModel by viewModels<MainViewModel>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StuffListUI(viewModel)
                }
            }
        }
    }
}

@Composable
fun StuffListUI(viewModel: MainViewModel) {

    val uiState = viewModel.uiState.collectAsState()

    DragDropColumn(
        items = uiState.value,
        onSwap = viewModel::swapSections
    ) { item ->
        Card(
            modifier = Modifier
                .clickable { viewModel.sectionClicked(item) },
        ) {
            Text(
                text = item.name,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(item.color))
                    .padding(16.dp),
            )
        }
    }
}