package mmswflow.chessandroidgame.ui_components.text


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun ScreenTitleText(text: Int, modifier: Modifier = Modifier){

    Text(
        text= stringResource(id = text),
        color= MaterialTheme.colorScheme.onSecondary,
        fontSize= ScreenTitleTextFontSize.value.sp,
        modifier= modifier
    )
}