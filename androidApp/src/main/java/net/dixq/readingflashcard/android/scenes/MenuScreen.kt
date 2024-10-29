package net.dixq.readingflashcard.android.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.dixq.readingflashcard.android.Data

@Composable
fun MenuScreen(onItemSelected: (Int, Int) -> Unit) {
    val titles = Data.getTitles()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(titles) { index, item ->
                Button(
                    onClick = { onItemSelected(SceneId.PlayCard.ordinal, index) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "レベル "+(index+1))
                }
                Spacer(modifier = Modifier.height(16.dp)) // ボタン間の隙間を設定
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onItemSelected(SceneId.Record.ordinal, -1) },
        ) {
            Text(text = "記録")
        }
    }
}