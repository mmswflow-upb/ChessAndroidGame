package mmswflow.chessandroidgame.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel

@Composable
fun GameScreen(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){

    //Set this up here so that our users cant leave the game by swiping, only by pressing a specific button
    BackHandler(enabled= true) {

    }

}