package mmswflow.chessandroidgame.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.ui_components.chessboard.ChessBoard
import mmswflow.chessandroidgame.ui_components.chessboard.PlayerInfoCard
import mmswflow.chessandroidgame.ui_components.utility.GameEndDialog

@Composable
fun GameScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    Log.d("GAME_SCREEN_TEST","Game Screen Loaded")

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
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                PlayerInfoCard(player = gameViewModel.player2.value!!)
            }
            //Row for Board and current game menu
            Row(
                modifier= Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChessBoard(gameViewModel= gameViewModel, modifier= Modifier)
            }

            //Player 1 Info
            Row(
                modifier= Modifier.fillMaxWidth()
            ) {
                PlayerInfoCard(player= gameViewModel.player1.value!!)
            }
        }
    }


}