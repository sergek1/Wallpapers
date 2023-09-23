package com.example.phonewalls.presentation.category_wallpapers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.model.Wallpaper

@Composable
fun WallpaperItem(
    wallpaper: Wallpaper,
    onItemClick: (Wallpaper) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .clickable { onItemClick(wallpaper) },
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        AsyncImage(
            model = wallpaper.url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            color = Color.Black,
            text = wallpaper.description.toString(),
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp)
        )
    }
}