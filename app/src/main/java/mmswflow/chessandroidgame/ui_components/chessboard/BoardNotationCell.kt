package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BoardNotationCell(
    notation: String,
    zAngle: Float
){


    //We will have notation cells that actually wont contain any text in them

    Text(text= notation,
        color= MaterialTheme.colorScheme.onSecondary,

        modifier= Modifier.size(8.dp).background( if(notation.length > 0) MaterialTheme.colorScheme.background else Color.Transparent)
            .graphicsLayer{ rotationZ= zAngle },

        textAlign = TextAlign.Center
    )

}
