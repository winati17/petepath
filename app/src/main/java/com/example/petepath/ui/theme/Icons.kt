package com.example.petepath.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.R
import com.example.petepath.Screen

@Composable
fun Pete2Icon() {
    Image(
        painter = painterResource(id = R.drawable.vector_pete2),
        contentDescription = "Pete-pete",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun HomepageIcon(active: Boolean = false, navController: NavController) {
    Image(
        painter = painterResource(
            id = if (active) R.drawable.vector_homepage else R.drawable.unactive_homepage
        ),
        contentDescription = "Homepage",
        modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(Screen.Home.route)
            }
    )
}
@Composable
fun HistoryIcon(active: Boolean = false, navController: NavController) {
    Image(
        painter = painterResource(
            id = if (active) R.drawable.vector_history else R.drawable.unactive_history
        ),
        contentDescription = "History",
        modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(Screen.History.route)
            }
    )
}

@Composable
fun ReportIcon(active: Boolean = false, navController: NavController) {
    Image(
        painter = painterResource(
            id = if (active) R.drawable.vector_report else R.drawable.unactive_report
        ),
        contentDescription = "Report",
        modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(Screen.ReportHistory.route)
            }
    )
}

@Composable
fun ProfileIcon(active: Boolean = false, navController: NavController) {
    Image(
        painter = painterResource(
            id = if (active) R.drawable.vector_profile else R.drawable.unactive_profile
        ),
        contentDescription = "Profile",
        modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(Screen.Profile.route)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPreviewPeteIcon() {
    Column{
        Spacer(modifier = Modifier.height(16.dp))
        Pete2Icon()
        Spacer(modifier = Modifier.height(16.dp))
        HomepageIcon(active = false, navController = rememberNavController())
        Spacer(modifier = Modifier.height(16.dp))
        HistoryIcon(active = true, navController = rememberNavController())
        Spacer(modifier = Modifier.height(16.dp))
        ReportIcon(active = true, navController = rememberNavController())
        Spacer(modifier = Modifier.height(16.dp))
        ProfileIcon(active = true, navController = rememberNavController())
    }
}