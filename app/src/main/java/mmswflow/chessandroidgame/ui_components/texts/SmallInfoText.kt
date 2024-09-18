package mmswflow.chessandroidgame.ui_components.texts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
@Composable
fun SmallInfoText(text: String){

    Text(
        text= text,
        color= MaterialTheme.colorScheme.onSecondary,
        fontSize= SmallInfoTextFontSize.value.sp,
        modifier= Modifier.padding(SmallInfoTextPadding.value.dp),
        textAlign = TextAlign.Center
    )

}