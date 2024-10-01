package net.dixq.readingflashcard.android

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.dixq.readingflashcard.android.scenes.MenuScreen
import net.dixq.readingflashcard.android.scenes.PlayCardScreen
import net.dixq.readingflashcard.android.scenes.RecordScreen
import net.dixq.readingflashcard.android.scenes.ResultScene
import net.dixq.readingflashcard.android.scenes.SceneId
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MainScreen(context: Context, tts: TextToSpeech) {
    var sceneId by remember { mutableIntStateOf(SceneId.Menu.ordinal) }
    var albumId by remember { mutableIntStateOf(-1) }

    when(sceneId) {
        SceneId.Menu.ordinal -> {
            MenuScreen(onItemSelected = { scene, album ->
                sceneId = scene
                albumId = album
            })
        }
        SceneId.PlayCard.ordinal -> {
            PlayCardScreen(
                albumId,
                tts,
                onBack = {
                    val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                    CoroutineScope(Dispatchers.IO).launch {
                        saveTimestamp(context, currentTime)
                    }
                    sceneId = SceneId.Menu.ordinal
                },
                onNext = {
                    sceneId = SceneId.Result.ordinal
                },
                context
            )
        }
        SceneId.Result.ordinal -> {
            ResultScene(
                onBack = { sceneId = SceneId.Menu.ordinal },
                albumId
            )
        }
        SceneId.Record.ordinal -> {
            RecordScreen(
                context,
                onBack = { sceneId = SceneId.Menu.ordinal }
            )
        }
    }
}


// DataStoreのセットアップ
val Context.dataStoreTimeStampRecords: DataStore<Preferences> by preferencesDataStore(name = "timestamps")

// 時刻を保存する関数
suspend fun saveTimestamp(context: Context, timestamp: String) {
    val key = stringSetPreferencesKey("timestamps")
    context.dataStoreTimeStampRecords.edit { preferences ->
        val currentTimestamps = preferences[key] ?: emptySet()
        preferences[key] = currentTimestamps + timestamp
        Log.e("ReadingFlashcards", "preferences[key] : "+preferences[key])
    }
}
