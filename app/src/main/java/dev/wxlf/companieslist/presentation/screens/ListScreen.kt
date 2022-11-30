package dev.wxlf.companieslist.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.wxlf.companieslist.presentation.models.DisplayableCompanyItem
import dev.wxlf.companieslist.presentation.viewmodels.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel) {
    val itemsList = List(15) {
        DisplayableCompanyItem("0", "By Park Inn.Ижевск", "https://lifehack.studio/test_task/test_images/1.jpg")
    }

    LazyColumn() {
        itemsList.forEach {
            item {
                ListItem(
                    headlineText = { Text(text = it.name, fontSize = 24.sp) },
                    leadingContent = {
                        AsyncImage(
                            modifier = Modifier.height(72.dp),
                            model = it.img,
                            contentDescription = "${it.name} Image"
                        )
                    },
                    modifier = Modifier.clickable {  }
                )
            }
        }
    }
}