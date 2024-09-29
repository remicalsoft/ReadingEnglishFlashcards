package net.dixq.readingflashcard.android.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Menu(onLevelSelected: (Int) -> Unit) {
    val levels = listOf("[a]からはじまる英単語", "マイクラ英文章")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        levels.forEachIndexed { index, level ->
            Button(onClick = { onLevelSelected(index) }) {
                Text(text = level)
            }
        }
    }
}