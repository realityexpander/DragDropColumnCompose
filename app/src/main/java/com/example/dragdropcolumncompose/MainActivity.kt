package com.example.dragdropcolumncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dragdropcolumncompose.ui.theme.DragDropColumnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragDropColumnComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")

                    StuffListUI()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DragDropColumnComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun StuffListUI() {

    //val viewModel: SectionListViewModel = hiltViewModel()
    //val uiState by viewModel.uiState.collectAsState()
    val viewModel by viewModels<MainViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    DragDropColumn(
        items = uiState.sections,
        onSwap = viewModel::swapSections
    ) { item ->
        Card(
            modifier = Modifier
                .clickable { viewModel.sectionClick(item) },
        ) {
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}