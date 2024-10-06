package mmswflow.chessandroidgame.chess_game_classes

import mmswflow.chessandroidgame.R

class Rook(
    val rColor: PieceColor,
    val rPosition: PiecePosition,
    var firstMove: Boolean = true,
    val rListOfPositionsThatCanSaveKing :MutableList<PiecePosition> = mutableListOf()
): ChessPiece(rColor, R.drawable.rook , rPosition, rListOfPositionsThatCanSaveKing) {

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

        return newPossiblePositions

    }

    override fun getAllLegalNewPositions(
        chessBoard: ChessBoard,
        enPassantEdiblePiece: Pawn?,
        underCheck: Boolean
    ): List<PiecePosition> {


        return getAllPossibleNewPositions(chessBoard, enPassantEdiblePiece, underCheck).filter {
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

        return Rook(rColor, position.deepClone(), firstMove, deepCloneListOfPositions(listOfPositionsThatCanSaveKing))
    }

    override fun toString() : String {
        return "${color.name} Rook at $position, first move: $firstMove"
    }
}