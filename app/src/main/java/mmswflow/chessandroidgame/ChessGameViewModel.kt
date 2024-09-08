package mmswflow.chessandroidgame

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mmswflow.chessandroidgame.data.ChessBoard
import mmswflow.chessandroidgame.data.GameMode
import mmswflow.chessandroidgame.data.GameTheme
import mmswflow.chessandroidgame.data.HistoryOfGameMoves
import mmswflow.chessandroidgame.data.Screen

class ChessGameViewModel: ViewModel(){

    val gameMode: MutableState<GameMode?> = mutableStateOf<GameMode?>(null)
    val currentScreen: MutableState<Screen> = mutableStateOf<Screen>(Screen.Home)

    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)

    //In millis
    val whiteTimeRemaining: MutableState<Long> = mutableStateOf(0L)
    val blackTimeRemaining: MutableState<Long> = mutableStateOf(0L)



    val chessBoard: MutableState<ChessBoard?> = mutableStateOf<ChessBoard?>(null)
    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)


}
