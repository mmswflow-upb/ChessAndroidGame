package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.chess_game_classes.BoardCell
import mmswflow.chessandroidgame.chess_game_classes.PieceColor

@Composable
fun ChessBoard(
    gameViewModel: ChessGameViewModel,
    modifier: Modifier
){

    if(gameViewModel.chessBoard.value != null){

        val rowNotation = listOf("","1", "2", "3", "4", "5", "6", "7", "8","")
        val columnNotation = listOf("","a", "b", "c", "d", "e", "f", "g", "h","")
        val whoPlays = gameViewModel.whoPlays.value

        val zAngle = if(whoPlays!!.color == PieceColor.White) 0f else 180f

        LazyVerticalGrid(
            modifier= modifier,
            columns = GridCells.Fixed(10)
        ){

            //Display Row of Notations for the columns
            columnNotation.forEach{
                    cellNotation->
                    item{
                        BoardNotationCell(notation= cellNotation, zAngle = zAngle)
                    }
            }

            //Display each row of the chessboard, with the row notation on each end

            for(row in 0..7){

                item{
                    BoardNotationCell(notation= rowNotation[row], zAngle= zAngle)
                }
                for (column in 0..7){

                    val cell = gameViewModel.chessBoard.value!!.boardMatrix[row][column]

                    item{
                        ChessBoardCell(
                            cell= cell,
                            whoPlays= gameViewModel.whoPlays.value!!.color,
                            selectedChessPiece= gameViewModel.selectedChessPiece
                        )
                    }
                }
                item{
                    BoardNotationCell(notation= rowNotation[row], zAngle= zAngle)
                }
            }

            //Display Row of Notations for the columns (again)
            columnNotation.forEach{
                    cellNotation->
                item{
                    BoardNotationCell(notation= cellNotation, zAngle = zAngle)
                }
            }
        }
    }

}
