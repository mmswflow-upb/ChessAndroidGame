package mmswflow.chessandroidgame

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
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
    val onlineMode: MutableState<Boolean> = mutableStateOf<Boolean>(false)

    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)

    //In seconds
    val whiteTimeRemaining: MutableState<Int> = mutableIntStateOf(0)
    val blackTimeRemaining: MutableState<Int> = mutableIntStateOf(0)

    val currentAvailableGameModes: MutableState<List<GameMode>> = mutableStateOf(listOf(GameMode.Classic, GameMode.Rapid,GameMode.Blitz, GameMode.Bullet,GameMode.Edit) )

    val chessBoard: MutableState<ChessBoard?> = mutableStateOf<ChessBoard?>(null)
    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)

    @OptIn(ExperimentalMaterial3Api::class)
    val carouselState = CarouselState(itemCount= {currentAvailableGameModes.value.count()})

}
