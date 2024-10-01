package mmswflow.chessandroidgame.chess_game_classes

import mmswflow.chessandroidgame.R

class Bishop(
    val bColor: PieceColor,
    val bPosition: PiecePosition,
    val bListOfPositionsThatCanSaveKing :MutableList<PiecePosition> = mutableListOf()
    ): ChessPiece(bColor, R.drawable.bishop, bPosition, bListOfPositionsThatCanSaveKing) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
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

            offset++

            val currentPiece = chessBoard.boardMatrix[this.position.row+offset][this.position.column-offset].occupyingPiece

            if(currentPiece != null){

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column-offset))
                break

            }else{

                newPossiblePositions.add(PiecePosition(row= this.position.row+offset, column= this.position.column-offset))
            }
        }

        offset = 0


        while(this.position.row - offset > 0 && this.position.column + offset < 7){

            offset++


            val currentPiece = chessBoard.boardMatrix[this.position.row-offset][this.position.column+offset].occupyingPiece

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
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
    ): List<PiecePosition> {


        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece, underCheck).filter {
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

        return Bishop(bColor, PiecePosition(bPosition.row,bPosition.column), deepCloneListOfPositions(listOfPositionsThatCanSaveKing))
    }

    override fun toString() : String {
        return "${color.name} Bishop at $position"
    }
}