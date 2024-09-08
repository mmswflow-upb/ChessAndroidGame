package mmswflow.chessandroidgame.ChessGameClasses

import androidx.annotation.DrawableRes
import mmswflow.chessandroidgame.data.ChessBoard


enum class PieceColor{

    White,
    Black
}

//row and column must range from 0 to 7
class PiecePosition(val row: Int, val column: Int){

    fun getColumnLetter(): String{
       return('a' + column).toString()
    }
}


abstract class ChessPiece(var color: PieceColor, @DrawableRes var icon: Int, var position: PiecePosition) {

    //This checks whether the move would be legal excluding the check/checkmate condition

    abstract fun checkIfPieceMoveIsLegal(chessBoard: ChessBoard, newPosition: PiecePosition, enPassantEdiblePiece: Pawn?): Boolean
    abstract fun getAllPossibleNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn?): List<PiecePosition>

}

