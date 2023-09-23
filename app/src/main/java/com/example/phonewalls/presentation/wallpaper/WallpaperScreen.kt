package com.example.phonewalls.presentation.wallpaper

import android.app.Activity
import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.AsyncImage
import com.example.phonewalls.presentation.wallpaper.viewmodel.WallpaperViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import java.io.IOException
import java.net.URL

@Composable
fun WallpaperScreen(
    viewModel: WallpaperViewModel = koinViewModel(),
) {
    val state = viewModel.state.value
    val myContext = LocalContext.current
    val wallpaperManager = WallpaperManager.getInstance(myContext)
    val window = (myContext as Activity).window
    val view = LocalView.current
    val coroutineScope = rememberCoroutineScope()
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, view).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.statusBars())
        Color.Transparent.toArgb()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = state.wallpaper,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Button(
            onClick = {
                val imageUrl = state.wallpaper
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        val bitmap = try {
                            val inputStream = URL(imageUrl).openStream()
                            BitmapFactory.decodeStream(inputStream)
                        } catch (e: IOException) {
                            null
                        }

                        if (bitmap != null) {
                            try {
                                wallpaperManager.setBitmap(bitmap)
                            } catch (e: IOException) {
                                Log.d("Установка обоев", e.message.toString())
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.Gray
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(
                text = "Установить как обои главного экрана",
                color = Color.LightGray,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp)
            )
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
