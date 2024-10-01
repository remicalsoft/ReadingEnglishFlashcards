package net.dixq.readingflashcard.android.scenes

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.dixq.readingflashcard.android.dataStoreTimeStampRecords

@Composable
fun RecordScreen(context: Context, onBack: () -> Unit) {
    val timestamps by getTimestamps(context).collectAsState(initial = emptySet())

    Log.e("ReadingFlashcards", "timestamps : $timestamps")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(timestamps.toList()) { timestamp ->
                Text(
                    timestamp,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.typography.bodyMedium.color,
                )
            }
        }
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

// 保存された時刻を読み込む関数
fun getTimestamps(context: Context): Flow<Set<String>> {
    val key = stringSetPreferencesKey("timestamps")
    return context.dataStoreTimeStampRecords.data.map { preferences ->
        preferences[key] ?: emptySet()
    }
}