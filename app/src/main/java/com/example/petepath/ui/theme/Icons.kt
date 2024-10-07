package com.example.petepath.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petepath.R

@Composable
fun Pete2Icon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.vector_pete2),
        contentDescription = "Pete-pete",
        modifier = modifier.size(48.dp)
    )
}

@Preview
@Composable
fun PreviewPeteIcon() {
    Pete2Icon()
}