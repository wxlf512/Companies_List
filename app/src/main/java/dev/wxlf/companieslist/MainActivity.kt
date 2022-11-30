@file:OptIn(ExperimentalMaterial3Api::class)

package dev.wxlf.companieslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.companieslist.presentation.screens.DetailsScreen
import dev.wxlf.companieslist.presentation.screens.ListScreen
import dev.wxlf.companieslist.presentation.ui.theme.CompaniesListTheme
import dev.wxlf.companieslist.presentation.viewmodels.DetailsViewModel
import dev.wxlf.companieslist.presentation.viewmodels.ListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompaniesListTheme {
                val systemUiController = rememberSystemUiController()
                val systemBarColor = Color.Transparent
                val isDarkMode = isSystemInDarkTheme()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = systemBarColor,
                        darkIcons = !isDarkMode
                    )
                    systemUiController.setNavigationBarColor(
                        color = systemBarColor,
                        darkIcons = !isDarkMode
                    )
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
        @Suppress("UNUSED_EXPRESSION")
        paddingValues // Ignore unnecessary value
        NavHost(
            navController = navController,
            startDestination = "companieslist://list"
        ) {
            composable("companieslist://list") {
                val listViewModel = hiltViewModel<ListViewModel>()
                ListScreen(listViewModel, navController)
            }
            composable(
                "companieslist://details/{id}", arguments = listOf(navArgument("id") {
                    type = NavType.StringType
                })
            ) {
                val detailsViewModel = hiltViewModel<DetailsViewModel>()
                DetailsScreen(detailsViewModel, it.arguments?.getString("id") ?: "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompaniesListTheme {
        MainScreen()
    }
}