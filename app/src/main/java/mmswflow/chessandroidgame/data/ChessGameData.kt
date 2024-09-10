package mmswflow.chessandroidgame.data

import androidx.compose.ui.graphics.Color
import mmswflow.chessandroidgame.ChessGameClasses.ChessPiece
import mmswflow.chessandroidgame.ChessGameClasses.PieceColor
import mmswflow.chessandroidgame.ChessGameClasses.PiecePosition
import mmswflow.chessandroidgame.R



sealed class GameMode(
    val name: Int,
    val timeLimit: Int //in Seconds
){

    object Classic : GameMode(name = R.string.classic_game_mode, timeLimit = 30 * 60)
    object Rapid: GameMode(name = R.string.rapid_game_mode, timeLimit = 10 * 60)
    object Bullet: GameMode(name = R.string.bullet_game_mode, timeLimit = 1 * 60)
}

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
