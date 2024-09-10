package mmswflow.chessandroidgame.chess_game_classes

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class Bishop(val bColor: PieceColor, val bPosition: PiecePosition): ChessPiece(bColor, R.drawable.ic_launcher_background , bPosition) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition>{

        val newPossiblePositions = mutableListOf<PiecePosition>()

        var offset = 0

        while(this.position.row + offset < 7 && this.position.column + offset < 7){

            offset++


            val currentPiece = chessBoard.boardMatrix.get(this.position.row+offset).get(this.position.column+offset).occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column+offset))
                break

            }else{

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column+offset))
            }
        }
        offset = 0

        while(this.position.row +offset < 7 && this.position.column - offset > 0){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row+offset).get(this.position.column-offset).occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column-offset))
                break

            }else{

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column-offset))
            }
        }

        offset = 0


        while(this.position.row - offset > 0 && this.position.column + offset < 7){

            val currentPiece = chessBoard.boardMatrix.get(this.position.row-offset).get(this.position.column+offset).occupyingPiece

            if(currentPiece != null){


                newPossiblePositions.add(PiecePosition(row= this.position.row-offset, column= this.position.column+offset))
                break

            }else{

                newPossiblePositions.add(PiecePosition(row= this.position.row-offset, column= this.position.column+offset))
            }
        }

        offset = 0


        while(this.position.row -offset > 0 && this.position.column -offset > 0){

            offset++

            val currentPiece = chessBoard.boardMatrix.get(this.position.row-offset).get(this.position.column-offset).occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row-offset, column= this.position.column-offset))
                break

            }else{

                newPossiblePositions.add(PiecePosition(row= this.position.row-offset, column= this.position.column-offset))
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