package mmswflow.chessandroidgame.ChessGameClasses

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class Rook(val rColor: PieceColor, val rPosition: PiecePosition, var firstMove: Boolean ): ChessPiece(rColor, R.drawable.ic_launcher_background , rPosition) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition>{

        val newPossiblePositions = mutableListOf<PiecePosition>()


        for(row in this.position.row..7){

            val currentPiece = chessBoard.boardMatrix.get(row).get(this.position.column).occupyingPiece
            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))
                break //We cant go past this position since the path is blocked
            }else{

                newPossiblePositions.add(PiecePosition(row= row, column= this.position.column))
            }
        }


        for(row in this.position.row downTo 0){

            val currentPiece = chessBoard.boardMatrix.get(row).get(this.position.column).occupyingPiece
            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))
                break //We cant go past this position since the path is blocked
            }else{

                newPossiblePositions.add(PiecePosition(row= row, column= this.position.column))
            }
        }

        for(column in this.position.column .. 7){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row).get(column).occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
                break

            }else{
                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
            }
        }

        for(column in this.position.column downTo 0){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row).get(column).occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
                break

            }else{
                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
            }
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