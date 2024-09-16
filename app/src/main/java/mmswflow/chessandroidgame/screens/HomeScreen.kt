package mmswflow.chessandroidgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.app_data.Screen
import mmswflow.chessandroidgame.ui_components.button.MediumActionButton
import mmswflow.chessandroidgame.ui_components.text.SmallInfoText
import mmswflow.chessandroidgame.ui_components.text.TextHeader
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun HomeScreen(
    navHost: NavHostController
){

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background,
    ){

        LazyColumn(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            item{

                TextHeader(text = stringResource(id = R.string.app_title))
            }

            item {
                MediumActionButton(
                    clickAction = {navHost.navigate(Screen.GameMultiplayerSelection.route)},
                    buttonText = R.string.play_button_text,
                    modifier= Modifier.width(MediumActionButtonWidth.value.dp)
                )
            }


            item{
                MediumActionButton(
                    clickAction = {navHost.navigate(Screen.GamesHistoryList.route)},
                    buttonText = R.string.games_history_button_text,
                    modifier= Modifier.width(MediumActionButtonWidth.value.dp)
                )
            }

            item{
                MediumActionButton(
                    clickAction = {navHost.navigate(Screen.GameSettings.route)},
                    buttonText = R.string.settings_button_text,
                    modifier = Modifier.width(MediumActionButtonWidth.value.dp)
                )
            }

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