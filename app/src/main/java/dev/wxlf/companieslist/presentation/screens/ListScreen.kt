package dev.wxlf.companieslist.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import dev.wxlf.companieslist.R
import dev.wxlf.companieslist.presentation.common.CompaniesListEvent
import dev.wxlf.companieslist.presentation.common.CompaniesListViewState
import dev.wxlf.companieslist.presentation.viewmodels.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is CompaniesListViewState.Error -> {
            Text((uiState as CompaniesListViewState.Error).msg)
        }
        is CompaniesListViewState.Loaded -> {
            val itemsList = (uiState as CompaniesListViewState.Loaded).companiesList
            Scaffold (
                topBar = {
                    TopAppBar(
                        title = { Text("Список компаний") }
                    )
                }
            ) {
                LazyColumn(Modifier.padding(it)) {
                    if (itemsList.isNotEmpty()) {
                        itemsList.forEach {
                            item {
                                ListItem(
                                    headlineText = { Text(text = it.name, fontSize = 24.sp) },
                                    leadingContent = {
                                        AsyncImage(
                                            modifier = Modifier
                                                .height(72.dp)
                                                .width(128.dp),
                                            model = "https://lifehack.studio/test_task/${it.img}",
                                            contentDescription = "${it.name} Image",
                                            error = painterResource(id = R.drawable.error_placeholder)
                                        )
                                    },
                                    modifier = Modifier.clickable { navController.navigate("companieslist://details/${it.id}") }
                                )
                            }
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