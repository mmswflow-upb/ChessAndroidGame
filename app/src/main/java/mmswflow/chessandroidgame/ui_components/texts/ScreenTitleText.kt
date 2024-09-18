package mmswflow.chessandroidgame.ui_components.texts


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun ScreenTitleText(text: String, modifier: Modifier = Modifier){

    Text(
        text= text,
        color= MaterialTheme.colorScheme.onSecondary,
        fontSize= ScreenTitleTextFontSize.value.sp,
        modifier= modifier
    )
}