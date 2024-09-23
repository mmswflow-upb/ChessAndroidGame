package mmswflow.chessandroidgame.chess_game_classes

import android.util.Log
import mmswflow.chessandroidgame.R
import java.util.stream.Collectors

class Pawn(val pColor: PieceColor, val pPosition: PiecePosition, var firstMove: Boolean = true): ChessPiece(pColor,R.drawable.pawn , pPosition){

    override fun getAllPossibleNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn? ): List<PiecePosition>{

        val newPossiblePositions = mutableListOf<PiecePosition>()

        //Separate behavior for white and black (only chess piece that cant go backwards)
        if(this.color == PieceColor.White){

            //Can it move upward or not, the piece might have moved before so we don't want it to go beyond the board
            if(this.position.row+1 <= 7){

                //Check if there's a piece directly above this pawn
                val firstBlockingPiece: ChessPiece? = chessBoard.boardMatrix[position.row+1][position.column].occupyingPiece

                if(firstBlockingPiece == null){
                    newPossiblePositions.add(PiecePosition(row= this.position.row+1, column= this.position.column))
                }


                //Then we check if it can move sideways one square up or not
                //This can happen only if there's a piece of the opposite color & it doesn't go outside the board
                // (even the king, the eat function will eliminate the possibility of the king being eaten for all pieces)

                if(this.position.column +1 <= 7){

                    val rightDiagonalPiece : ChessPiece? = chessBoard.boardMatrix[position.row+1][position.column+1].occupyingPiece

                    //Check whether there's a piece on the right diagonal or not, if there is we move there
                    //If there isn't, there might be a pawn that can be eaten using en passant
                    if(rightDiagonalPiece != null){

                        newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column+1))

                    }else{
                        if(enPassantEdiblePiece != null){
                            //Check if enPassant edible piece is on its right
                            if(enPassantEdiblePiece.position.row == this.position.row
                                && enPassantEdiblePiece.position.column == this.position.column+1
                                && enPassantEdiblePiece.color != this.color){

                                newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column+1))
                            }
                        }
                    }
                }

                //Check whether there's a piece on the left diagonal or not, if there is we move there
                if(this.position.column-1 >= 0){

                    val leftDiagonalPiece : ChessPiece? = chessBoard.boardMatrix[position.row+1][position.column-1].occupyingPiece

                    if(leftDiagonalPiece != null){

                        newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column-1))

                    }else{
                        if(enPassantEdiblePiece != null){

                            //Check if enPassant edible piece is on its left
                            if(enPassantEdiblePiece.position.row == this.position.row
                                && enPassantEdiblePiece.position.column == this.position.column-1
                                && enPassantEdiblePiece.color != this.color){
                                newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column-1))
                            }
                        }
                    }
                }
            }



            if(firstMove) {

                //If the second upward piece exists, we can't move upward two squares

                val secondBlockingPiece: ChessPiece? =
                    chessBoard.boardMatrix[position.row + 2][position.column].occupyingPiece


                if (secondBlockingPiece == null) {

                    newPossiblePositions.add(
                        PiecePosition(
                            row = this.position.row + 2,
                            column = this.position.column
                        )
                    )
                }

            }



        }else{
            //Black Colored Piece

            //Can it move downward or not, the piece might have moved before so we don't want it to go beyond the board
            if(this.position.row -1 >= 0){

                //Check if there's a piece directly below this pawn
                val firstBlockingPiece: ChessPiece? = chessBoard.boardMatrix[this.position.row-1][this.position.column].occupyingPiece

                if(firstBlockingPiece == null){
                    newPossiblePositions.add(PiecePosition(row= this.position.row-1, column= this.position.column))
                }


                //Then we check if it can move sideways one square down or not
                //This can happen only if there's a piece of the opposite color & it doesn't go outside the board
                // (even the king, the eat function will eliminate the possibility of the king being eaten for all pieces)

                if(this.position.column -1 >= 0){

                    val rightDiagonalPiece : ChessPiece? = chessBoard.boardMatrix[position.row-1][position.column-1].occupyingPiece

                    //Check whether there's a piece on the right diagonal or not, if there is we move there
                    //If there isn't, there might be a pawn that can be eaten using en passant
                    if(rightDiagonalPiece != null){

                        newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column-1))


                    }else{
                        if(enPassantEdiblePiece != null){
                            //Check if enPassant edible piece is on its right
                            if(enPassantEdiblePiece.position.row == this.position.row
                                && enPassantEdiblePiece.position.column == this.position.column-1
                                && enPassantEdiblePiece.color != this.color){

                                newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column-1))
                            }
                        }
                    }
                }

                if(this.position.column+1 <= 7){

                    val leftDiagonalPiece : ChessPiece? = chessBoard.boardMatrix[position.row-1][position.column+1].occupyingPiece

                    if(leftDiagonalPiece != null){

                        newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column+1))

                    }else{
                        if(enPassantEdiblePiece != null) {


                            //Check if enPassant edible piece is on its left
                            if (enPassantEdiblePiece.position.row == this.position.row
                                && enPassantEdiblePiece.position.column == this.position.column + 1
                                && enPassantEdiblePiece.color != this.color
                            ) {

                                newPossiblePositions.add(PiecePosition(row = this.position.row - 1, column = this.position.column + 1))
                            }
                        }
                    }
                }
            }


            if(firstMove) {

                //If the second downward piece exists, we can't move downward

                val secondBlockingPiece: ChessPiece? =
                    chessBoard.boardMatrix[position.row - 2][position.column].occupyingPiece


                if (secondBlockingPiece == null) {

                    newPossiblePositions.add(
                        PiecePosition(
                            row = this.position.row -2,
                            column = this.position.column
                        )
                    )
                }

            }

        }

        return newPossiblePositions
    }

    override fun getAllLegalNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?
    ): List<PiecePosition> {


        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece).filter {
            val currentPiece = chessBoard.boardMatrix[it.row][it.column].occupyingPiece

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
        enPassantEdiblePiece: Pawn?,
    ): Boolean {

        return getAllLegalNewPositions(chessBoard, enPassantEdiblePiece).any{it.row == newPosition.row && it.column == newPosition.column}
    }

    override fun protectsPosition(
        chessBoard: ChessBoard,
        protectedPiecePosition: PiecePosition
    ): Boolean {
        return getAllPossibleNewPositions(chessBoard, null)
            .any{ it.row == protectedPiecePosition.row && it.column == protectedPiecePosition.column}
    }

    override fun deepClone(): ChessPiece {

        return King(pColor, PiecePosition(pPosition.row,pPosition.column), firstMove)
    }
}