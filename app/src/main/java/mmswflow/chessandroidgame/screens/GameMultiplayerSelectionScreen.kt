package mmswflow.chessandroidgame.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.GameMode
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.ui_components.utility.ScreenTopBar
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionCard
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionIconIdentifier

@Composable
fun GameMultiplayerSelectionScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    //Ensure that back stack doesn't get filled for no reason
    BackHandler {
        navHost.popBackStack()
    }

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background,
    ) {

        Scaffold(
            topBar= {
                ScreenTopBar(
                    screenTitle = R.string.game_multiplayer_selection_screen_title,
                    navHost= navHost,
                    navIcon = Icons.Filled.Menu
                )
            }
        ) {
            paddingValues->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                item{
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SelectionCard(
                            identifier = { SelectionIconIdentifier(icon = R.drawable.offline, tint= MaterialTheme.colorScheme.error) },
                            description = R.string.offline_mode_description,
                            actionOnSelection = {
                                gameViewModel.onlineMode.value = false
                                gameViewModel.currentAvailableGameModes.value = listOf(GameMode.Classic, GameMode.Rapid,GameMode.Blitz, GameMode.Bullet,GameMode.Edit)
                                navHost.navigate(Screen.GameModeSelection.route)
                            },
                            modifier= Modifier.weight(1f)
                        )

                        SelectionCard(
                            identifier = { SelectionIconIdentifier(icon = R.drawable.online, tint= MaterialTheme.colorScheme.onPrimary) },
                            description = R.string.online_mode_description,
                            actionOnSelection = {
                                gameViewModel.onlineMode.value = true
                                gameViewModel.currentAvailableGameModes.value = listOf(GameMode.Classic, GameMode.Rapid,GameMode.Blitz, GameMode.Bullet)
                                navHost.navigate(Screen.GameModeSelection.route)
                            },
                            modifier= Modifier.weight(1f)
                        )
                    }
                }
            }
        }

    }
}