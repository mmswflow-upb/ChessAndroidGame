package mmswflow.chessandroidgame.chess_game_classes

import mmswflow.chessandroidgame.R

class Queen(
    val qColor: PieceColor,
    val qPosition: PiecePosition,
    val qListOfPositionsThatCanSaveKing :MutableList<PiecePosition> = mutableListOf()
): ChessPiece(qColor, R.drawable.queen , qPosition, qListOfPositionsThatCanSaveKing) {

    override fun getAllPossibleNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
    ): List<PiecePosition>{
        val newPossiblePositions = mutableListOf<PiecePosition>()

        for(row in this.position.row+1..7){

            val currentPiece = chessBoard.boardMatrix[row][this.position.column].occupyingPiece
            newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))

            if(currentPiece != null){
                break //We cant go past this position since the path is blocked
            }
        }


        for(row in this.position.row-1 downTo 0){

            val currentPiece = chessBoard.boardMatrix[row][this.position.column].occupyingPiece
            newPossiblePositions.add(PiecePosition(row= row, column = this.position.column))

            if(currentPiece != null){
                break //We cant go past this position since the path is blocked
            }
        }

        for(column in this.position.column+1 .. 7){

            val currentPiece = chessBoard.boardMatrix[this.position.row][column].occupyingPiece
            newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))

            if(currentPiece != null){
                break
            }
        }

        for(column in this.position.column-1 downTo 0){

            val currentPiece = chessBoard.boardMatrix[this.position.row][column].occupyingPiece
            newPossiblePositions.add(PiecePosition(row= this.position.row, column= column))

            if(currentPiece != null){
                break
            }
        }


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

        return Queen(qColor, position.deepClone(), deepCloneListOfPositions(listOfPositionsThatCanSaveKing))
    }

    override fun toString() : String {
        return "${color.name} Queen at $position"
    }
}