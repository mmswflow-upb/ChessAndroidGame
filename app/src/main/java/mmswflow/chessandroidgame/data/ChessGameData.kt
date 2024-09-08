package mmswflow.chessandroidgame.data

import mmswflow.chessandroidgame.ChessGameClasses.ChessPiece
import mmswflow.chessandroidgame.ChessGameClasses.PieceColor
import mmswflow.chessandroidgame.ChessGameClasses.PiecePosition
import mmswflow.chessandroidgame.R



sealed class GameMode(
    val name: Int,
    val timeLimit: Long //in Millis
){

    object Classic : GameMode(name = R.string.classic_game_mode, timeLimit = 30)
    object Rapid: GameMode(name = R.string.rapid_game_mode, timeLimit = 10)
    object Bullet: GameMode(name = R.string.bullet_game_mode, timeLimit = 1)
}

data class BoardCell(val position: PiecePosition, val occupyingPiece: ChessPiece?)

data class ChessBoard(
    val boardMatrix: Array<Array<BoardCell>> =
        Array(8){
            row -> Array(8) {
            column -> BoardCell(position= PiecePosition(row= row,column= column), null)
        }
        }
)

data class Move(
    val player: PieceColor,
    val whiteTime: Long,
    val blackTime: Long,
    val movedPiece: ChessPiece,
    val oldPosition: PiecePosition,
    val newPosition: PiecePosition
)

data class HistoryOfGameMoves(val moves: Array<Move>, val gameMode: GameMode)
