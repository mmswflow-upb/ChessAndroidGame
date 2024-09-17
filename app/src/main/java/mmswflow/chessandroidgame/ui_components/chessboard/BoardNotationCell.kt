package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun BoardNotationCell(
    notation: String,
    zAngle: Float
){


    //We will have notation cells that actually wont contain any text in them
    Box(modifier= Modifier
        .size(ChessBoardCellSize.value.dp)
        .background(if (notation.isNotEmpty()) MaterialTheme.colorScheme.background else Color.Transparent)
        .graphicsLayer { rotationZ = zAngle },
        contentAlignment = Alignment.Center
    ){
        Text(
            text= notation,
            color= MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}
