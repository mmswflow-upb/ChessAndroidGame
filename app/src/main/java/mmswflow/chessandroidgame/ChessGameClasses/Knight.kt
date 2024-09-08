package mmswflow.chessandroidgame.ChessGameClasses

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class Knight(val kColor: PieceColor, val kPosition: PiecePosition): ChessPiece(kColor, R.drawable.ic_launcher_background , kPosition) {


    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition> {

        val newPossiblePositions = mutableListOf<PiecePosition>(
            PiecePosition(row= this.position.row+2, column= this.position.column+1),
            PiecePosition(row= this.position.row+2, column= this.position.column-1),
            PiecePosition(row= this.position.row+1, column= this.position.column+2),
            PiecePosition(row= this.position.row-1, column= this.position.column+2),
            PiecePosition(row= this.position.row+1, column= this.position.column-2),
            PiecePosition(row= this.position.row-1, column= this.position.column-2),
            PiecePosition(row= this.position.row-2, column= this.position.column+1),
            PiecePosition(row= this.position.row-2, column= this.position.column-1)
        )



       newPossiblePositions.filter {
           if(it.row in 0..7 && it.column in 0..7){
               val targetPiece = chessBoard.boardMatrix.get(it.row).get(it.column).occupyingPiece
               if(targetPiece != null){

                    targetPiece.color != this.color
               }else{
                   true
               }

           }else{
               false
           }
       }


        return newPossiblePositions
    }

    override fun checkIfPieceMoveIsLegal(
        chessBoard: ChessBoard,
        newPosition: PiecePosition,
        enPassantEdiblePiece: Pawn?
    ): Boolean {

        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece).contains(newPosition)
    }

}