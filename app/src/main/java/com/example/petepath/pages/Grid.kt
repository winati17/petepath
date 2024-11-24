import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridPreview() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Set the number of columns
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Spacing between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Spacing between columns
    ) {
        // Generate grid items
        items(20) { index ->
            GridItem(number = index + 1)
        }
    }
}

@Composable
fun GridItem(number: Int) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // Ensure each grid item is square
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        BasicText(
            text = "Item $number",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridPreviewPreview() {
    GridPreview()
}
