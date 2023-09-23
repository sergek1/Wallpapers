package com.example.phonewalls.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonewalls.presentation.categories.CategoriesScreen
import com.example.phonewalls.presentation.category_wallpapers.CategoryScreen
import com.example.phonewalls.presentation.wallpaper.WallpaperScreen
import com.example.phonewalls.ui.theme.PhoneWallsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneWallsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CategoriesScreen.route
                    ) {
                        composable(
                            route = Screen.CategoriesScreen.route
                        ) {
                            CategoriesScreen(navController)
                        }

                        composable(
                            route = Screen.CategoryScreen.route + "/{topicId}"
                        ) {
                            CategoryScreen(navController)
                        }

                        composable(
                            route = Screen.WallpaperScreen.route + "/{wallpaperId}"
                        ) {
                            WallpaperScreen()
                        }
                    }
                }
            }
        }
    }
}