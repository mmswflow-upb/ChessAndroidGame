package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//We're going to pass from the view Model the time directly

fun convertTimeToMinsAndSec(timeSec: Int): String{

        val minutes = timeSec/60
        val seconds = timeSec % 60

        return String.format("%02d:%02d", minutes, seconds)
}


@Composable
fun TimerUI(
    time: Int,
    maxTime: Int,
    active: Boolean
){
    Box(modifier = Modifier.fillMaxSize()){

        LinearProgressIndicator(progress=  time.toFloat()/maxTime.toFloat() , modifier= Modifier.fillMaxWidth())
        Text(text = convertTimeToMinsAndSec(timeSec= time), modifier = Modifier.size(16.dp))
    }

}