package mmswflow.chessandroidgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.ui_components.LargeInfoText
import mmswflow.chessandroidgame.ui_components.SelectionCard
import mmswflow.chessandroidgame.ui_components.SelectionIconIdentifier

@Composable
fun GameMultiplayerSelectionScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        LargeInfoText(text = R.string.game_multiplayer_selection_screen_title)
                
        Row(
            
        ){
            SelectionCard(identifier ={ SelectionIconIdentifier(icon = R.drawable.offline)}, description = R.string.offline_mode_description) {
                    
            }
                
            SelectionCard(identifier = { SelectionIconIdentifier(icon = R.drawable.online)}, description = R.string.online_mode_description) {

            }
        }

    }
}