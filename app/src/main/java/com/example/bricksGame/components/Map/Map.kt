package com.example.bricksGame.components.Map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.errorContainerLight

@Composable
fun Map() {
    Box(Modifier.fillMaxSize(),

     ) {
     LazyVerticalGrid(
      modifier = Modifier.fillMaxSize()
       .background(errorContainerLight)
       .padding(50.dp)
      ,
      columns = GridCells.Adaptive(20.dp)

     ) {

     }
    }
}