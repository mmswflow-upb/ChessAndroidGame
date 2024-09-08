package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.R

@Composable
fun SmallActionButton(
    clickAction: () -> Unit,
    buttonText: Int
){

    Button(
        onClick= { clickAction() },
        modifier= Modifier
            .width(8.dp)
            .height(6.dp)
            .background(MaterialTheme.colorScheme.primary)
    ){

        Text(text= stringResource(id= buttonText), color= MaterialTheme.colorScheme.onPrimary)
    }
}