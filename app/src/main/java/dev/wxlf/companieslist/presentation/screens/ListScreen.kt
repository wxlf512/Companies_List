package dev.wxlf.companieslist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.wxlf.companieslist.presentation.viewmodels.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello, Android",
                modifier = Modifier.padding(it)
            )
        }
    }
}