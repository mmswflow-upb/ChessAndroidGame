package mmswflow.chessandroidgame.ui_components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun TextHeader(
    text: String
){

    Text(text= text,
        modifier= Modifier.padding(bottom= HeaderPaddingSize.value.dp),
        fontSize = HeaderFontSize.value.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        color= MaterialTheme.colorScheme.onSurface,

    )
}