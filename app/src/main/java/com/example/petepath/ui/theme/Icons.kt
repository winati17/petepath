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

@Composable
fun HomepageIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.vector_homepage),
        contentDescription = "Homepage",
        modifier = modifier.size(24.dp)
    )
}

@Composable
fun HistoryIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.vector_history),
        contentDescription = "History",
        modifier = modifier.size(24.dp)
    )
}

@Composable
fun ReportIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.vector_report),
        contentDescription = "Report",
        modifier = modifier.size(24.dp)
    )
}

@Composable
fun ProfileIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.vector_profile),
        contentDescription = "Profile",
        modifier = Modifier.size(24.dp)
    )
}
@Preview
@Composable
fun PreviewPeteIcon() {
    Pete2Icon()
    HomepageIcon()
    HistoryIcon()
    ReportIcon()
    ProfileIcon()
}