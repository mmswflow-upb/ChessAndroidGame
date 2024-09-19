package mmswflow.chessandroidgame.ui_components.screens_utils


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui.theme.BrightRed
import mmswflow.chessandroidgame.ui.theme.DarkBlueGray
import mmswflow.chessandroidgame.ui.theme.DarkGreen
import mmswflow.chessandroidgame.ui.theme.DarkOrange
import mmswflow.chessandroidgame.ui.theme.DarkRed
import mmswflow.chessandroidgame.ui.theme.DarkYellow
import mmswflow.chessandroidgame.ui.theme.LightGray
import mmswflow.chessandroidgame.ui.theme.LightGreen
import mmswflow.chessandroidgame.ui.theme.LightYellow
import mmswflow.chessandroidgame.ui.theme.WarningOrange
import mmswflow.chessandroidgame.ui.theme.White
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

//We're going to pass from the view Model the time directly

fun convertTimeToMinsAndSec(timeSec: Int): String{

        val minutes = timeSec/60
        val seconds = timeSec % 60

        return String.format("%02d:%02d", minutes, seconds)
}

fun getTimerColors(progress : Float) : Pair<Color,Color> {

    val color = if(progress > 0.75f){
        Pair(DarkGreen,LightGreen)
    }else if(0.5f <= progress){

        Pair(DarkYellow, LightYellow)

    }else if(progress >= 0.25) {

        Pair(DarkOrange,WarningOrange)

    }else{

        Pair(DarkRed,BrightRed)
    }

    return color
}

@Composable
fun TimerUI(
    time: Int,
    maxTime: Int,
    active: Boolean,
    modifier: Modifier = Modifier
){
    val progress= time.toFloat()/maxTime.toFloat()
    val colorsPair = if(active) getTimerColors(progress) else Pair(DarkBlueGray,LightGray)

    Box(
        contentAlignment = Alignment.Center,
        modifier= modifier
    ){

        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(TimerSize.value.dp),
            trackColor = colorsPair.first,
            color= colorsPair.second,
            strokeWidth = TimerIndicatorStrokeWidth.value.dp
        )
        Text(
            text = convertTimeToMinsAndSec(timeSec= time),
            fontSize = TimerTextFontSize.value.sp,
            color= if(active) White else LightGray
        )
    }

}