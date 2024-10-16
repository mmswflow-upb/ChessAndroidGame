package mmswflow.chessandroidgame.chess_game_classes

import android.util.Log
import mmswflow.chessandroidgame.R

class King(
    val kgColor: PieceColor,
    val kgPosition: PiecePosition,
    var firstMove: Boolean  = true,
    val kgListOfPositionsThatCanSaveKing :MutableList<PiecePosition> = mutableListOf()
): ChessPiece(kgColor, R.drawable.king , kgPosition, kgListOfPositionsThatCanSaveKing) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?,
        underCheck : Boolean
    ): List<PiecePosition> {
        val newPossiblePositions: MutableList<PiecePosition> = mutableListOf<PiecePosition>(
            PiecePosition(this.position.row+1,this.position.column+1),
            PiecePosition(this.position.row+1,this.position.column-1),
            PiecePosition(this.position.row+1, this.position.column),
            PiecePosition(this.position.row, this.position.column-1),
            PiecePosition(this.position.row,this.position.column+1),
            PiecePosition(this.position.row-1,this.position.column+1),
            PiecePosition(this.position.row-1,this.position.column-1),
            PiecePosition(this.position.row-1, this.position.column)
        ).filter {
            it.row in 0..7 && it.column in 0..7
        }.toMutableList()


        //Castling condition, if the king didn't move before castling MIGHT be allowed

        if(this.firstMove && !underCheck){

            //The rooks might have moved from their starting positions
            val closeRook = chessBoard.boardMatrix[this.position.row][this.position.column+3].occupyingPiece
            val farRook = chessBoard.boardMatrix[this.position.row][this.position.column-4].occupyingPiece

            if(closeRook != null){

                if(closeRook is Rook){

                    if(closeRook.firstMove){

                        //Check if the path is clear for the castling
                        if(closeRook.protectsPosition(chessBoard, this.position)){

                            newPossiblePositions.add(PiecePosition(this.position.row, this.position.column+2))

                        }
                    }

                }
            }

            if(farRook != null){

                if(farRook is Rook){

                    if(farRook.firstMove){

                        //Check if the path is clear for the castling
                        if(farRook.protectsPosition(chessBoard, this.position)) {

                            newPossiblePositions.add(PiecePosition(this.position.row, this.position.column-2))
                        }

                    }

                }
            }
        }

        return newPossiblePositions
    }

    override fun getAllLegalNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
    ): List<PiecePosition> {


        //Filter out the positions that are occupied by pieces of the same color
        //then filter out the positions which are protected by any of the other enemy pieces

        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece, underCheck).filter {
            val currentPiece = chessBoard.boardMatrix.get(it.row).get(it.column).occupyingPiece

            if(currentPiece != null){
                if(currentPiece.color != this.color){

                    //Check whether it's protected by any other enemy piece
                    if(this.color == PieceColor.White){

                        for(enemyPiece in chessBoard.blackPieces){
                            if(enemyPiece != currentPiece){

                                if(enemyPiece.protectsPosition(chessBoard,currentPiece.position)){
                                    return@filter false
                                }
                            }
                        }
                        return@filter true
                    }else{
                        for(enemyPiece in chessBoard.whitePieces){
                            if(enemyPiece.protectsPosition(chessBoard, currentPiece.position)){
                                return@filter false
                            }
                        }
                        return@filter true
                    }
                }else{
                    return@filter false
                }

            }else{
                return@filter true
            }
        }
    }
    override fun isPieceMoveLegal(
        chessBoard: ChessBoard,
        newPosition: PiecePosition,
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
    ): Boolean {

        return getAllLegalNewPositions(chessBoard, enPassantEdiblePiece, underCheck).contains(newPosition)
    }

    override fun protectsPosition(
        chessBoard: ChessBoard,
        protectedPiecePosition: PiecePosition
    ): Boolean {
        return getAllPossibleNewPositions(chessBoard, null, false).contains(protectedPiecePosition)
    }

    override fun deepClone(): ChessPiece {
        return King(kgColor, position.deepClone(), firstMove, deepCloneListOfPositions(listOfPositionsThatCanSaveKing))
    }

    override fun toString() : String {
        return "${color.name} King at $position, first move: $firstMove"
    }
}