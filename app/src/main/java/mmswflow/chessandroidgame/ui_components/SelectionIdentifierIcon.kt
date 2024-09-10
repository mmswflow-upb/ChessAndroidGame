package mmswflow.chessandroidgame.ui_components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ui_components.SizingValue.*

@Composable
fun SelectionIconIdentifier(@DrawableRes icon: Int){

    Icon(
        painter= painterResource(id = icon),
        contentDescription = null,
        tint= MaterialTheme.colorScheme.onPrimary,
        modifier= Modifier.padding(SelectionIconIdentifierPadding.value.dp)
    )
}