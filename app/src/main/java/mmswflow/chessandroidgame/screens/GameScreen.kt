package mmswflow.chessandroidgame.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.ui_components.utility.GameEndDialog

@Composable
fun GameScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background
    ) {
        //Set this up here so that our users cant leave the game by swiping, only by pressing a specific button
        BackHandler(enabled= true){
            if(gameViewModel.gameEnded.value && !gameViewModel.displayGameEndedDialog.value){

                navHost.popBackStack()
            }
        }

        Column(
            modifier= Modifier.fillMaxSize()
        ) {

            if(gameViewModel.displayGameEndedDialog.value){

                GameEndDialog(
                    gameViewModel = gameViewModel,
                    navHost = navHost
                )
            }

            //Player 2 info
            Row(
                modifier= Modifier.fillMaxWidth()
            ) {

            }
            //Row for Board and current game menu
            Row(
                modifier= Modifier.fillMaxWidth()
            ) {

            }

            //Player 1 Info
            Row(
                modifier= Modifier.fillMaxWidth()
            ) {

            }
        }



    }


}