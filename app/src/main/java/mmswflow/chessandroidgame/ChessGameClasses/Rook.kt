package mmswflow.chessandroidgame.ChessGameClasses

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class Rook(val rColor: PieceColor, val rPosition: PiecePosition, val canCastle: Boolean ): ChessPiece(rColor, R.drawable.ic_launcher_background , rPosition) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition>{

        val newPossiblePositions = mutableListOf<PiecePosition>()

        //Go up the same column as this rook

        for(row in this.position.row..7){

            val currentPiece = chessBoard.boardMatrix.get(row).get(this.position.column).occupyingPiece
            if(currentPiece != null){

                if(currentPiece.color != this.color){

                    newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))
                }
                break //We cant go past this position since the path is blocked
            }else{

                newPossiblePositions.add(PiecePosition(row= row, column= this.position.column))
            }
        }

        //Go down the same column as this rook

        for(row in this.position.row downTo 0){

            val currentPiece = chessBoard.boardMatrix.get(row).get(this.position.column).occupyingPiece
            if(currentPiece != null){

                if(currentPiece.color != this.color){

                    newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))
                }
                break //We cant go past this position since the path is blocked
            }else{

                newPossiblePositions.add(PiecePosition(row= row, column= this.position.column))
            }
        }

        //Go right on the same row as this rook
        for(column in this.position.column .. 7){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row).get(column).occupyingPiece

            if(currentPiece != null){

                if(currentPiece.color != this.color){
                    newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
                }
                break

            }else{
                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
            }
        }

        //Go left on the same row as this rook
        for(column in this.position.column downTo 0){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row).get(column).occupyingPiece

            if(currentPiece != null){

                if(currentPiece.color != this.color){
                    newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
                }
                break

            }else{
                newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))
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