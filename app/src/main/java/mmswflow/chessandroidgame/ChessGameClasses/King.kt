package mmswflow.chessandroidgame.ChessGameClasses

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class King(val kgColor: PieceColor, val kgPosition: PiecePosition  ): ChessPiece(kgColor, R.drawable.ic_launcher_background , kgPosition) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition> {
        val newPossiblePositions = mutableListOf<PiecePosition>(
            PiecePosition(this.position.row+1,this.position.column+1),
            PiecePosition(this.position.row+1,this.position.column-1),
            PiecePosition(this.position.row+1, this.position.column),
            PiecePosition(this.position.row, this.position.column-1),
            PiecePosition(this.position.row,this.position.column+1),
            PiecePosition(this.position.row-1,this.position.column+1),
            PiecePosition(this.position.row-1,this.position.column-1),
            PiecePosition(this.position.row-1, this.position.column)
        ).filter {
            if(it.row in 0..7 && it.column in 0..7){

                true
            }else{
                false
            }
        }


        return newPossiblePositions
    }

    override fun getAllLegalNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition> {


        //Filter out the positions that are occupied by pieces of the same color
        //then filter out the positions which are protected by any of the other enemy pieces

        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece).filter {
            val currentPiece = chessBoard.boardMatrix.get(it.row).get(it.column).occupyingPiece

            if(currentPiece != null){
                if(currentPiece.color != this.color){

                    //Check whether it's protected by any other enemy piece
                    if(this.color == PieceColor.White){

                        for(enemyPiece in chessBoard.blackPieces){
                            if(enemyPiece != currentPiece){

                                if(enemyPiece.protectsPiece(chessBoard,currentPiece.position)){
                                    return@filter false
                                }
                            }
                        }
                        return@filter true
                    }else{
                        for(enemyPiece in chessBoard.whitePieces){
                            if(enemyPiece.protectsPiece(chessBoard, currentPiece.position)){
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
    override fun checkIfPieceMoveIsLegal(
        chessBoard: ChessBoard,
        newPosition: PiecePosition,
        enPassantEdiblePiece: Pawn?
    ): Boolean {

        return getAllLegalNewPositions(chessBoard, enPassantEdiblePiece).contains(newPosition)
    }

    override fun protectsPiece(
        chessBoard: ChessBoard,
        protectedPiecePosition: PiecePosition
    ): Boolean {
        return getAllPossibleNewPositions(chessBoard, null).contains(protectedPiecePosition)
    }

}