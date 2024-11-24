package com.example.petepath.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petepath.R

@Composable
fun Pete2Icon() {
    Image(
        painter = painterResource(id = R.drawable.vector_pete2),
        contentDescription = "Pete-pete",
        modifier = Modifier.size(48.dp)
    )
}

@Composable
fun HomepageIcon(active : Boolean = false) {
    if (active) {
        Image(
            painter = painterResource(id = R.drawable.vector_homepage),
            contentDescription = "Homepage",
            modifier = Modifier.size(24.dp)
        )
    } else{
        Image(
            painter = painterResource(id = R.drawable.unactive_homepage),
            contentDescription = "Homepage",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun HistoryIcon(active : Boolean = false) {
    if (active) {
        Image(
            painter = painterResource(id = R.drawable.vector_history),
            contentDescription = "History",
            modifier = Modifier.size(48.dp)
        )
    } else{
        Image(
            painter = painterResource(id = R.drawable.unactive_history),
            contentDescription = "History",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ReportIcon(active : Boolean = false) {
    if (active) {
        Image(
            painter = painterResource(id = R.drawable.vector_report),
            contentDescription = "Report",
            modifier = Modifier.size(48.dp)
        )
    } else{
        Image(
            painter = painterResource(id = R.drawable.unactive_report),
            contentDescription = "Report",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ProfileIcon(active : Boolean = false) {
    if (active) {
        Image(
            painter = painterResource(id = R.drawable.vector_profile),
            contentDescription = "Profile",
            modifier = Modifier.size(48.dp)
        )
    } else{
        Image(
            painter = painterResource(id = R.drawable.unactive_profile),
            contentDescription = "Profile",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPreviewPeteIcon() {
    Column{
        Spacer(modifier = Modifier.height(16.dp))
        Pete2Icon()
        Spacer(modifier = Modifier.height(16.dp))
        HomepageIcon(active = false)
        Spacer(modifier = Modifier.height(16.dp))
        HistoryIcon(active = true)
        Spacer(modifier = Modifier.height(16.dp))
        ReportIcon(active = true)
        Spacer(modifier = Modifier.height(16.dp))
        ProfileIcon(active = true)
    }
}