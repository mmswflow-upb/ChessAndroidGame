package mmswflow.chessandroidgame.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.ui_components.chessboard.ChessBoard
import mmswflow.chessandroidgame.ui_components.screens_utils.PlayerInfoCard
import mmswflow.chessandroidgame.ui_components.dialogs.GameEndDialog
import mmswflow.chessandroidgame.ui_components.UISizingValue.*

@Composable
fun GameScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    val player1 = gameViewModel.player1.value
    val playerInTurn = gameViewModel.playerInTurn.value
    val zAngle = if(playerInTurn == player1) 0f else 180f

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
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            if(gameViewModel.displayGameEndedDialog.value){

                GameEndDialog(
                    gameViewModel = gameViewModel,
                    zAngle= zAngle
                )
            }

            Row(modifier= Modifier
                .padding(PlayerInfoCardPadding.value.dp)
                .graphicsLayer{ rotationZ= zAngle }
            ) {
                //Player 2 info
                PlayerInfoCard(
                    player = gameViewModel.player2.value!!,
                    gameMode= gameViewModel.gameMode.value!!,
                    gameViewModel.player2RemainingTime.value
                )
            }

            //Row for Board and current game menu
            Row(
                modifier= Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChessBoard(gameViewModel= gameViewModel, modifier= Modifier.weight(1f), zAngle= zAngle)
            }

            Row(modifier= Modifier
                .padding(PlayerInfoCardPadding.value.dp)
                .graphicsLayer{ rotationZ= zAngle }
            ) {
                //Player 1 Info
                PlayerInfoCard(
                    player= gameViewModel.player1.value!!,
                    gameMode= gameViewModel.gameMode.value!!,
                    remainingTime = gameViewModel.player1RemainingTime.value
                )
            }

        }
    }
}