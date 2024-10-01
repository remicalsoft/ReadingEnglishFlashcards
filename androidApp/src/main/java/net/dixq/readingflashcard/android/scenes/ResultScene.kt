package net.dixq.readingflashcard.android.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.dixq.readingflashcard.android.Data

@Composable
fun ResultScene(onBack: () -> Unit, albumId: Int) {

    val list = Data.getWordsAndSentences(albumId)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(list) { item ->
                Text(
                    text = item,
                    color = MaterialTheme.typography.bodyMedium.color,
                    )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onBack() },
        ) {
            Text(text = "戻る")
        }
    }
}