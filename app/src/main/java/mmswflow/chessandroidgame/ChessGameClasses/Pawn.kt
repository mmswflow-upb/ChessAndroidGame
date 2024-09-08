package mmswflow.chessandroidgame.ChessGameClasses

import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.data.ChessBoard

class Pawn(val pColor: PieceColor, val pPosition: PiecePosition, var firstMove: Boolean): ChessPiece(pColor,R.drawable.ic_launcher_background , pPosition){

    override fun getAllPossibleNewPositions(chessBoard: ChessBoard, enPassantEdiblePiece: Pawn? ): List<PiecePosition>{

        val newPossiblePositions = mutableListOf<PiecePosition>()

        //Separate behavior for white and black (only chess piece that cant go backwards)
        if(this.color == PieceColor.White){

            //Can it move upward or not, the piece might have moved before so we don't want it to go beyond the board
            if(this.position.row+1 <= 7){

                //Check if there's a piece directly above this pawn
                val firstBlockingPiece: ChessPiece? = chessBoard.boardMatrix.get(position.row+1).get(position.column).occupyingPiece

                if(firstBlockingPiece == null){
                    newPossiblePositions.add(PiecePosition(row= this.position.row+1, column= this.position.column))
                }


                //Then we check if it can move sideways one square up or not
                //This can happen only if there's a piece of the opposite color & it doesn't go outside the board
                // (even the king, the eat function will eliminate the possibility of the king being eaten for all pieces)

                if(this.position.column +1 <= 7){

                    val rightDiagonalPiece : ChessPiece? = chessBoard.boardMatrix.get(position.row+1).get(position.column+1).occupyingPiece

                    //Check whether there's a piece on the right diagonal or not, if there is we move there
                    //If there isn't, there might be a pawn that can be eaten using en passant
                    if(rightDiagonalPiece != null){

                        if(rightDiagonalPiece.color != this.color){

                            newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column+1))
                        }
                    }else if(enPassantEdiblePiece != null){
                        //Check if enPassant edible piece is on its right
                        if(enPassantEdiblePiece.position.row == this.position.row
                            && enPassantEdiblePiece.position.column == this.position.column+1){

                            newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column+1))
                        }
                    }
                }

                //Check whether there's a piece on the left diagonal or not, if there is we move there
                if(this.position.column-1 >= 0){

                    val leftDiagonalPiece : ChessPiece? = chessBoard.boardMatrix.get(position.row+1).get(position.column-1).occupyingPiece

                    if(leftDiagonalPiece != null){
                        if(leftDiagonalPiece.color != this.color){

                            newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column-1))
                        }
                    }else if(enPassantEdiblePiece != null){


                        //Check if enPassant edible piece is on its left
                        if(enPassantEdiblePiece.position.row == this.position.row
                            && enPassantEdiblePiece.position.column == this.position.column-1){
                            newPossiblePositions.add(PiecePosition(row= this.position.row+1,column= this.position.column-1))
                        }
                    }
                }
            }



            if(firstMove) {

                //If the second upward piece exists, we can't move upward two squares

                val secondBlockingPiece: ChessPiece? =
                    chessBoard.boardMatrix.get(position.row + 2).get(position.column).occupyingPiece


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
                val firstBlockingPiece: ChessPiece? = chessBoard.boardMatrix.get(this.position.row-1).get(this.position.column).occupyingPiece

                if(firstBlockingPiece == null){
                    newPossiblePositions.add(PiecePosition(row= this.position.row-1, column= this.position.column))
                }


                //Then we check if it can move sideways one square down or not
                //This can happen only if there's a piece of the opposite color & it doesn't go outside the board
                // (even the king, the eat function will eliminate the possibility of the king being eaten for all pieces)

                if(this.position.column -1 >= 0){

                    val rightDiagonalPiece : ChessPiece? = chessBoard.boardMatrix.get(position.row-1).get(position.column-1).occupyingPiece

                    //Check whether there's a piece on the right diagonal or not, if there is we move there
                    //If there isn't, there might be a pawn that can be eaten using en passant
                    if(rightDiagonalPiece != null){

                        if(rightDiagonalPiece.color != this.color){

                            newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column-1))
                        }

                    }else if(enPassantEdiblePiece != null){
                        //Check if enPassant edible piece is on its right
                        if(enPassantEdiblePiece.position.row == this.position.row
                            && enPassantEdiblePiece.position.column == this.position.column-1){

                            newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column-1))
                        }
                    }
                }

                if(this.position.column+1 <= 7){

                    val leftDiagonalPiece : ChessPiece? = chessBoard.boardMatrix.get(position.row-1).get(position.column+1).occupyingPiece

                    if(leftDiagonalPiece != null){
                        if(leftDiagonalPiece.color != this.color){

                            newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column+1))
                        }
                    }else if(enPassantEdiblePiece != null){


                        //Check if enPassant edible piece is on its left
                        if(enPassantEdiblePiece.position.row == this.position.row
                            && enPassantEdiblePiece.position.column == this.position.column+1){

                            newPossiblePositions.add(PiecePosition(row= this.position.row-1,column= this.position.column+1))
                        }
                    }
                }
            }


            if(firstMove) {

                //If the second downward piece exists, we can't move downward

                val secondBlockingPiece: ChessPiece? =
                    chessBoard.boardMatrix.get(position.row - 2).get(position.column).occupyingPiece


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

    override fun checkIfPieceMoveIsLegal(
        chessBoard: ChessBoard,
        newPosition: PiecePosition,
        enPassantEdiblePiece: Pawn?,
    ): Boolean {

        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece).contains(newPosition)
    }
}