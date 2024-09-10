package mmswflow.chessandroidgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.ui_components.layout.ScreenTopBar
import mmswflow.chessandroidgame.ui_components.text.LargeInfoText
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionCard
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionIconIdentifier

@Composable
fun GameMultiplayerSelectionScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            item{

                ScreenTopBar(
                    screenTitle = R.string.game_multiplayer_selection_screen_title,
                    navHost= navHost,
                    navIcon = Icons.Filled.Menu
                )
            }

            item{
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SelectionCard(
                        identifier = { SelectionIconIdentifier(icon = R.drawable.offline, tint= MaterialTheme.colorScheme.error) },
                        description = R.string.offline_mode_description,
                        actionOnSelection = {
                            gameViewModel.onlineMode.value = false
                            navHost.navigate(Screen.GameModeSelection.route)
                        },
                        modifier= Modifier.weight(1f)
                    )

                    SelectionCard(
                        identifier = { SelectionIconIdentifier(icon = R.drawable.online, tint= MaterialTheme.colorScheme.onPrimary) },
                        description = R.string.online_mode_description,
                        actionOnSelection = {
                            gameViewModel.onlineMode.value = true
                            //TODO Add navigation to next screen
                        },
                        modifier= Modifier.weight(1f)
                    )
                }
            }
        }
    }
}