package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.SizingValue.*

@Composable
fun MediumInfoText(text: Int){

    Text(
        text= stringResource(id = text),
        color= MaterialTheme.colorScheme.onSecondary,
        fontSize= MediumInfoTextFontSize.value.sp,
        modifier= Modifier.padding(MediumInfoTextPadding.value.dp)
    )
}