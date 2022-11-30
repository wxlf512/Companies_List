@file:OptIn(ExperimentalMaterial3Api::class)

package dev.wxlf.companieslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.wxlf.companieslist.ui.theme.CompaniesListTheme

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Companies List") }
            )
        }
    ){
        NavHost(
            navController = navController,
            startDestination = "companieslist://list",
            modifier = Modifier.padding(paddingValues = it)
        ) {
            composable("companieslist://list") {
                TestScreen()
            }
        }
    }
}

@Composable
fun TestScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Text(
                text = "Hello, Android",
                modifier = Modifier.padding(it)
            )
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