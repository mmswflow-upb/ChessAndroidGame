package mmswflow.chessandroidgame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.ui_components.SmallActionButton
import mmswflow.chessandroidgame.ui_components.SmallInfoText
import mmswflow.chessandroidgame.ui_components.TextHeader
import mmswflow.chessandroidgame.ui_components.SizingValue.*

@Composable
fun HomeScreen(
    navHost: NavHostController
){

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background,
    ){

        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextHeader(text = stringResource(id = R.string.app_title))

            SmallActionButton(
                clickAction = {navHost.navigate(Screen.GameModeSelection.route)},
                buttonText = R.string.play_button_text,
                modifier= Modifier.width(SmallActionButtonWidth.value.dp)
            )

            SmallActionButton(
                clickAction = {navHost.navigate(Screen.GameModeSelection.route)},
                buttonText = R.string.edit_mode_button_text,
                modifier= Modifier.width(SmallActionButtonWidth.value.dp)
            )

            SmallActionButton(
                clickAction = {navHost.navigate(Screen.GameModeSelection.route)},
                buttonText = R.string.games_history_button_text,
                modifier= Modifier.width(SmallActionButtonWidth.value.dp)
            )
        }
        Row(
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ){
            SmallInfoText(text = R.string.author_title)
            SmallInfoText(text = R.string.version)
        }
    }
}