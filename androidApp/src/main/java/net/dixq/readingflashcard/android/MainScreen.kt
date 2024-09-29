package net.dixq.readingflashcard.android

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import net.dixq.readingflashcard.android.scenes.Menu
import net.dixq.readingflashcard.android.scenes.PlayCard

@Composable
fun MainScreen(tts: TextToSpeech) {
    var selectedLevelIndex by remember { mutableIntStateOf(-1) }

    if (selectedLevelIndex == -1) {
        Menu(onLevelSelected = { levelIndex ->
            selectedLevelIndex = levelIndex
        })
    } else {
        PlayCard(
            selectedLevelIndex = selectedLevelIndex,
            tts = tts,
            onBack = { selectedLevelIndex = -1 }
        )
    }
}
