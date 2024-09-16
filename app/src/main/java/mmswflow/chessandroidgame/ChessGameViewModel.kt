package mmswflow.chessandroidgame

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mmswflow.chessandroidgame.chess_game_classes.GameOperations
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.ChessBoard
import mmswflow.chessandroidgame.chess_game_classes.GameMode
import mmswflow.chessandroidgame.app_data.GameTheme
import mmswflow.chessandroidgame.chess_game_classes.HistoryOfGameMoves
import mmswflow.chessandroidgame.chess_game_classes.Player
import mmswflow.chessandroidgame.app_data.Screen
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece

class ChessGameViewModel: ViewModel(){


    //Current Game Related
    val onlineMode: MutableState<Boolean> = mutableStateOf<Boolean>(false)
    val gameMode: MutableState<GameMode?> = mutableStateOf<GameMode?>(null)
    val chessBoard: MutableState<ChessBoard?> = mutableStateOf<ChessBoard?>(null)
    val whoPlays: MutableState<Player?> = mutableStateOf<Player?>(null)
    val whiteTimeRemaining: MutableState<Int> = mutableIntStateOf(0)
    val blackTimeRemaining: MutableState<Int> = mutableIntStateOf(0)
    val gameOperations: MutableState<GameOperations?> = mutableStateOf<GameOperations?>(null)
    val displayGameEndedDialog: MutableState<Boolean> = mutableStateOf(false)
    val winnerColor : MutableState<PieceColor?> = mutableStateOf(null)
    val gameEnded: MutableState<Boolean> = mutableStateOf(false)
    val player1: MutableState<Player?> = mutableStateOf(null)
    val player2: MutableState<Player?> = mutableStateOf(null)
    val selectedChessPiece: MutableState<ChessPiece?> = mutableStateOf(null)

    //Selections Related
    val currentAvailableGameModes: MutableState<List<GameMode>> = mutableStateOf(listOf(
        GameMode.Classic, GameMode.Rapid,
        GameMode.Blitz, GameMode.Bullet,
        GameMode.Edit) )
    val currentScreen: MutableState<Screen> = mutableStateOf<Screen>(Screen.Home)
    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)
    @OptIn(ExperimentalMaterial3Api::class)
    val carouselState = CarouselState(itemCount= {currentAvailableGameModes.value.count()})


    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)


}
