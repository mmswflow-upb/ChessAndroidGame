package mmswflow.chessandroidgame

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.ChessBoard
import mmswflow.chessandroidgame.chess_game_classes.GameMode
import mmswflow.chessandroidgame.app_data.GameTheme
import mmswflow.chessandroidgame.chess_game_classes.HistoryOfGameMoves
import mmswflow.chessandroidgame.chess_game_classes.Player
import mmswflow.chessandroidgame.screens.Screen
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.chooseRandomColorOffline

class ChessGameViewModel: ViewModel(){


    //Current Game Related
    val onlineMode: MutableState<Boolean> = mutableStateOf(false)
    val gameMode: MutableState<GameMode?> = mutableStateOf(null)
    val chessBoard: MutableState<ChessBoard?> = mutableStateOf(null)
    val whoPlays: MutableState<Player?> = mutableStateOf(null)
    val whiteTimeRemaining: MutableState<Int> = mutableIntStateOf(0)
    val blackTimeRemaining: MutableState<Int> = mutableIntStateOf(0)
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
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)
    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)
    @OptIn(ExperimentalMaterial3Api::class)
    val carouselState = CarouselState(itemCount= {currentAvailableGameModes.value.count()})


    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)

    private fun getPlayerStats(name: String): List<Int>{

        val result = mutableListOf(0,0,0)

        if(onlineMode.value){

        }else{

        }

        return result
    }
    suspend fun setPlayers(){


        whiteTimeRemaining.value = gameMode.value!!.timeLimit
        blackTimeRemaining.value = gameMode.value!!.timeLimit

        val colorPair : Pair<PieceColor,PieceColor>

        val player1Name  : String
        val player2Name : String


        if(onlineMode.value){

            colorPair = chooseRandomColorOffline()
            player1Name = ""
            player2Name = ""

        }else{
            colorPair = chooseRandomColorOffline()
            player1Name = "Player 1"
            player2Name = "Player 2"
        }


        val player1Stats = getPlayerStats(player1Name)
        val player2Stats = getPlayerStats(player2Name)

        val player1Pieces = if(colorPair.first == PieceColor.White) chessBoard.value!!.whitePieces else chessBoard.value!!.blackPieces
        val player2Pieces = if(colorPair.second == PieceColor.White) chessBoard.value!!.whitePieces else chessBoard.value!!.blackPieces

        player1.value = Player(
            name= player1Name,
            color= colorPair.first,
            remainingTime= gameMode.value!!.timeLimit,
            wins= player1Stats[0],
            losses = player1Stats[1],
            draws= player1Stats[2],
            remainingPieces= player1Pieces,
            online= true
        )
        player2.value = Player(
            name= player2Name,
            color= colorPair.second,
            wins= player2Stats[0],
            losses = player2Stats[1],
            draws= player2Stats[2],
            remainingTime= gameMode.value!!.timeLimit,
            remainingPieces = player2Pieces,
            online= true
        )
        if(player1.value!!.color == PieceColor.White){
            whoPlays.value = player1.value
        }else{
            whoPlays.value = player2.value

        }

    }
}
