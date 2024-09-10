package mmswflow.chessandroidgame.chess_game_classes

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
    //Return all possible moves (even the ones in which in the new position there's a piece of the same color)
    abstract fun getAllPossibleNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn?): List<PiecePosition>
    //Return all legal moves in which we exclude the positions in which the occupying pieces are of the same color
    abstract fun getAllLegalNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn?): List<PiecePosition>

    //Returns whether an enemy piece is protected by another enemy piece
    abstract fun protectsPiece(chessBoard: ChessBoard, protectedPiecePosition: PiecePosition): Boolean
}

