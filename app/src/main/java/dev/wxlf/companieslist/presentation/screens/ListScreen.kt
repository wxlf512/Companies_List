package dev.wxlf.companieslist.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.wxlf.companieslist.presentation.common.CompaniesListEvent
import dev.wxlf.companieslist.presentation.common.CompaniesListViewState
import dev.wxlf.companieslist.presentation.viewmodels.ListViewModel
import dev.wxlf.companieslist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is CompaniesListViewState.Error -> {
            Text((uiState as CompaniesListViewState.Error).msg)
        }
        is CompaniesListViewState.Loaded -> {
            val itemsList = (uiState as CompaniesListViewState.Loaded).companiesList
            LazyColumn() {
                if (itemsList.isNotEmpty()) {
                    itemsList.forEach {
                        Log.e("AAAAAAA", it.toString())
                        item {
                            ListItem(
                                headlineText = { Text(text = it.name, fontSize = 24.sp) },
                                leadingContent = {
                                    AsyncImage(
                                        modifier = Modifier.height(72.dp).width(128.dp),
                                        model = "https://lifehack.studio/test_task/${it.img}",
                                        contentDescription = "${it.name} Image",
                                        error = painterResource(id = R.drawable.error_placeholder)
                                    )
                                },
                                modifier = Modifier.clickable { }
                            )
                        }
                    }
                }
            }
        }
        CompaniesListViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(CompaniesListEvent.ScreenShown)
    })
}