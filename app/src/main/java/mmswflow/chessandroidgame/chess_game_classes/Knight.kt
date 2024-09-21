package mmswflow.chessandroidgame.chess_game_classes

import mmswflow.chessandroidgame.R

class Knight(val kColor: PieceColor, val kPosition: PiecePosition): ChessPiece(kColor, R.drawable.knight , kPosition) {


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
        ).filter {
            it.row in 0..7 && it.column in 0..7
       }


        return newPossiblePositions
    }

    override fun getAllLegalNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition> {


        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece).filter {
            val currentPiece = chessBoard.boardMatrix.get(it.row).get(it.column).occupyingPiece

            if(currentPiece != null){
                currentPiece.color != this.color

            }else{
                true
            }
        }
    }
    override fun isPieceMoveLegal(
        chessBoard: ChessBoard,
        newPosition: PiecePosition,
        enPassantEdiblePiece: Pawn?
    ): Boolean {

        return getAllLegalNewPositions(chessBoard, enPassantEdiblePiece).any{it.row == newPosition.row && it.column == newPosition.column}
    }

    override fun protectsPosition(
        chessBoard: ChessBoard,
        protectedPiecePosition: PiecePosition
    ): Boolean {
        return getAllPossibleNewPositions(chessBoard, null).any{it.row == protectedPiecePosition.row && it.column == protectedPiecePosition.column}
    }
}