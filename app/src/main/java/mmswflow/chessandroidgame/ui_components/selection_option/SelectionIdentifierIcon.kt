package mmswflow.chessandroidgame.ui_components.selection_option

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun SelectionIconIdentifier(@DrawableRes icon: Int, tint: Color){

    Icon(
        painter= painterResource(id = icon),
        contentDescription = null,
        tint= tint,
        modifier= Modifier.padding(SelectionIconIdentifierPadding.value.dp)
    )
}