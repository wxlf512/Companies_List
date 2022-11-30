package dev.wxlf.companieslist.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.wxlf.companieslist.R
import dev.wxlf.companieslist.presentation.common.DetailsEvent
import dev.wxlf.companieslist.presentation.common.DetailsViewState
import dev.wxlf.companieslist.presentation.models.DisplayableDetailsModel
import dev.wxlf.companieslist.presentation.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, id: String) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is DetailsViewState.Error -> {
            Text((uiState as DetailsViewState.Error).msg)
        }
        is DetailsViewState.Loaded -> {
            val details = (uiState as DetailsViewState.Loaded).details
            Details(details)
        }
        DetailsViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(DetailsEvent.ScreenShown(id = id))
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(details: DisplayableDetailsModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = details.name, fontSize = 36.sp) }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .height(392.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    model = "https://lifehack.studio/test_task/${details.img}",
                    contentDescription = "${details.name} Image",
                    error = painterResource(id = R.drawable.error_placeholder)
                )
            }
            item {
                Text(
                    text = details.description,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )
            }
            if(details.lat != 0.0) {
                item {
                    val uriHandler = LocalUriHandler.current
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { uriHandler.openUri("geo:${details.lat},${details.lon}") }
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.height(32.dp),
                        )
                        Text(
                            text = "${details.lat} ${details.lon}",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            if (details.www.isNotEmpty()) {
                item {
                    val uriHandler = LocalUriHandler.current
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { uriHandler.openUri("https://${details.www}") },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_baseline_link_24),
                            contentDescription = "Link",
                        )
                        Text(
                            text = details.www,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            if (details.phone.isNotEmpty()) {
                item {
                    val uriHandler = LocalUriHandler.current
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { uriHandler.openUri("tel:${details.phone}") },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Link",
                        )
                        Text(
                            text = details.phone,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TestDetails() {
    Details(
        details = DisplayableDetailsModel(
            "name",
            "https://lifehack.studio/test_task/test_images/1.jpg",
            "description",
            55.555555,
            55.555555,
            "www.aaa.com",
            "+7(111)111-11-11"
        )
    )
}
