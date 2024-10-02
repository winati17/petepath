package com.example.petepath.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Scrollable(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)

//@Composable
//fun (modifier: Modifier = Modifier) {
//
//}