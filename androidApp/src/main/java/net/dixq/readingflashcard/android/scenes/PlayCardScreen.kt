package net.dixq.readingflashcard.android.scenes

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.dixq.readingflashcard.android.Data
import net.dixq.readingflashcard.android.R
import net.dixq.readingflashcard.android.scenes.Defines.buttonRound
import net.dixq.readingflashcard.android.scenes.Defines.buttonSize


// 共通のフォントサイズ定義
object Defines {
    val FontSizeContent = 24.sp
    val buttonSize = Modifier.size(120.dp, 120.dp)
    val buttonRound = RoundedCornerShape(4.dp)
}

@Composable
fun PlayCardScreen(
    albumId: Int,
    tts: TextToSpeech,
    onBack: () -> Unit,
    onNext: () -> Unit,
    context: Context
) {
    BackHandler(onBack = { onBack() })

    // 選択されたレベルの単語リストを抽出
    val words = Data.getWordsAndSentences(albumId)
    var currentIndex by remember { mutableIntStateOf(0) }
    var isPlayButtonClicked by remember { mutableStateOf(false) }

    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.result) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 進捗バーの追加
        LinearProgressIndicator(
            progress = currentIndex / (words.size * 2).toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = words[currentIndex % words.size],
            fontSize = Defines.FontSizeContent,
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.typography.bodyMedium.color,
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) currentIndex--
                },
                modifier = buttonSize,
                shape = buttonRound
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back"
                )
            }

            Button(
                onClick = {
                    tts.speak(words[currentIndex % words.size], TextToSpeech.QUEUE_FLUSH, null, null)
                    isPlayButtonClicked = true
                },
                modifier = buttonSize,
                shape = buttonRound
            ) {
                Image(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "play"
                )
            }

            Button(
                onClick = {
                    if (currentIndex == words.size * 2 - 1){
                        mediaPlayer.start() // 効果音を再生
                        onNext()
                    } else {
                        currentIndex++
                        isPlayButtonClicked = false
                    }
                },
                modifier = buttonSize,
                shape = buttonRound,
                enabled = isPlayButtonClicked // playボタンが押されるまで無効
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = "forward"
                )
            }
        }
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

