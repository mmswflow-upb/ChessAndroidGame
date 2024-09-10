package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BoardNotationCell(
    notation: String,
){


    Text(text= notation,
        color= MaterialTheme.colorScheme.onSecondary,
        modifier= Modifier.size(8.dp).background(MaterialTheme.colorScheme.background),
        textAlign = TextAlign.Center
    )

}
