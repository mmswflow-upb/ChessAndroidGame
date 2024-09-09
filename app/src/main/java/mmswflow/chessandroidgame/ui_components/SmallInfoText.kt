package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallInfoText(text: Int){

    Text(
        text= stringResource(id = text),
        color= MaterialTheme.colorScheme.onSecondary,
        fontSize= SizingValue.SmallInfoTextFontSize.value.sp,
        modifier= Modifier.padding(SizingValue.SmallInfoTextPadding.value.dp)
    )
}