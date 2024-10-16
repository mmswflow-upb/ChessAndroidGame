package mmswflow.chessandroidgame.chess_game_classes

import androidx.annotation.DrawableRes


enum class PieceColor{

    White,
    Black
}

//row and column must range from 0 to 7
class PiecePosition(val row: Int, val column: Int){


    fun getColumnLetter(): String{

       return('a' + column).toString()
    }

    fun deepClone(): PiecePosition{
        return PiecePosition(row, column)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is PiecePosition) return false
        return row == other.row && column == other.column
    }

    //Sanity check 0_0
    override fun hashCode(): Int {
        return 31* row + column
    }

    override fun toString() : String {
        return "(${row+1}, ${getColumnLetter()})"
    }
}


abstract class ChessPiece(
    var color: PieceColor,
    @DrawableRes var icon: Int,
    var position: PiecePosition,
    val listOfPositionsThatCanSaveKing : MutableList<PiecePosition>
) {

    //This checks whether the move would be legal excluding the check/checkmate condition

    abstract fun isPieceMoveLegal(chessBoard: ChessBoard, newPosition: PiecePosition, enPassantEdiblePiece: Pawn?,underCheck: Boolean= false ): Boolean
    //Return all possible moves (even the ones in which in the new position there's a piece of the same color)
    abstract fun getAllPossibleNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn?, underCheck: Boolean= false): List<PiecePosition>
    //Return all legal moves in which we exclude the positions in which the occupying pieces are of the same color
    abstract fun getAllLegalNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn?, underCheck: Boolean= false): List<PiecePosition>

    //Returns whether a piece is protected by another piece
    abstract fun protectsPosition(chessBoard: ChessBoard, protectedPiecePosition: PiecePosition): Boolean

    //Returns a deep clone of the chess piece object
    abstract fun deepClone(): ChessPiece

    abstract override fun toString(): String
}

fun deepCloneListOfPositions(listOfPositions: List<PiecePosition>) : MutableList<PiecePosition>{

    val clonedList = mutableListOf<PiecePosition>()

    for(pos in listOfPositions){
        clonedList.add(PiecePosition(pos.row,pos.column))
    }

    return clonedList
}

fun deepCloneListOfPieces(listOfPieces: List<ChessPiece>) : MutableList<ChessPiece>{

    val clonedList = mutableListOf<ChessPiece>()

    for (piece in listOfPieces){
        clonedList.add(piece.deepClone())
    }

    return clonedList
}

val startingWhitePieces = mutableListOf<ChessPiece>(

    // Pawns
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,0) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,1) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,2) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,3) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,4) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,5) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,6) ),
    Pawn( pColor= PieceColor.White, pPosition= PiecePosition(1,7) ),

    // Rooks
    Rook( rColor= PieceColor.White, rPosition= PiecePosition(0,0) ),
    Rook( rColor= PieceColor.White, rPosition= PiecePosition(0,7) ),

    // Knights
    Knight( kColor= PieceColor.White, kPosition= PiecePosition(0,1) ),
    Knight( kColor= PieceColor.White, kPosition= PiecePosition(0,6) ),

    // Bishops
    Bishop( bColor= PieceColor.White, bPosition= PiecePosition(0,2)),
    Bishop( bColor= PieceColor.White, bPosition= PiecePosition(0,5)),

    // Queen
    Queen( qColor= PieceColor.White, qPosition= PiecePosition(0,3)),

    // King
    King( kgColor= PieceColor.White, kgPosition= PiecePosition(0,4) ))

val startingBlackPieces = mutableListOf<ChessPiece>(

    // Pawns
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,0) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,1) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,2) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,3) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,4) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,5) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,6) ),
    Pawn( pColor= PieceColor.Black, pPosition= PiecePosition(6,7) ),

// Rooks
    Rook( rColor= PieceColor.Black, rPosition= PiecePosition(7,0) ),
    Rook( rColor= PieceColor.Black, rPosition= PiecePosition(7,7) ),

// Knights
    Knight( kColor= PieceColor.Black, kPosition= PiecePosition(7,1) ),
    Knight( kColor= PieceColor.Black, kPosition= PiecePosition(7,6) ),

// Bishops
    Bishop( bColor= PieceColor.Black, bPosition= PiecePosition(7,2) ),
    Bishop( bColor= PieceColor.Black, bPosition= PiecePosition(7,5) ),

// Queen
    Queen( qColor= PieceColor.Black, qPosition= PiecePosition(7,3) ),

// King
    King( kgColor= PieceColor.Black, kgPosition= PiecePosition(7,4) )

)