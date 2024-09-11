package mmswflow.chessandroidgame.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.PiecePosition
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.ui.theme.BrightRed
import mmswflow.chessandroidgame.ui.theme.LightBlue
import mmswflow.chessandroidgame.ui.theme.LightGreen
import mmswflow.chessandroidgame.ui.theme.WarningOrange


sealed class GameMode(
    val name: Int,
    val timeLimit: Int ,//in Seconds
    val logo: Int,
    val description: Int,
    val tint: Color
){

    object Classic : GameMode(
        name = R.string.classic_game_mode,
        timeLimit = 30 * 60,
        logo= R.drawable.turtle,
        description= R.string.classic_game_mode_description,
        tint= LightGreen
    )
    object Rapid: GameMode(
        name = R.string.rapid_game_mode,
        timeLimit = 10 * 60,
        logo= R.drawable.rabbit,
        description= R.string.rapid_game_mode_description ,
        tint= LightBlue
    )
    object Blitz: GameMode(
        name=R.string.blitz_game_mode,
        timeLimit= 5 * 60,
        logo= R.drawable.blitz,
        description= R.string.blitz_game_mode_description,
        tint= WarningOrange
    )
    object Bullet: GameMode(
        name = R.string.bullet_game_mode,
        timeLimit = 1 * 60,
        logo= R.drawable.bullet,
        description= R.string.bullet_game_mode_description,
        tint= BrightRed
    )
    object Edit: GameMode(
        name=R.string.edit_game_mode,
        timeLimit= 0,
        logo= R.drawable.edit,
        description= R.string.edit_game_mode_description,
        tint= Color.Yellow
    )
}

val listOfGameModes = listOf(GameMode.Classic,GameMode.Rapid,GameMode.Blitz,GameMode.Bullet)

//Cell's color depends on the sum of the row and column, if it's even it's black, otherwise it's white
data class BoardCell(val position: PiecePosition, val cellColor: Color, var occupyingPiece: ChessPiece?)

//Might use later for online implementation
//data class Player(val color: PieceColor, val pieces: MutableList<ChessPiece>)

data class ChessBoard(
    val boardMatrix: Array<Array<BoardCell>> =
        Array(8){
            row -> Array(8) {
            column ->
                BoardCell(
                    position = PiecePosition(row= row,column= column),
                    cellColor = if((row+column) % 2 == 0) Color.Black else Color.White,
                    null
                )
        }
        },
    val whitePieces: List<ChessPiece>,
    val blackPieces: List<ChessPiece>
)

data class Move(
    val playerToMove: PieceColor,
    val whiteTime: Int,
    val blackTime: Int,
    val movedPiece: ChessPiece,
    val newPosition: PiecePosition,
)

//The starting board is needed because a player could choose to use the "edit" mode and then play with the setup they've created
data class HistoryOfGameMoves(val startingBoard: ChessBoard,val moves: MutableList<Move>, val gameMode: GameMode)
