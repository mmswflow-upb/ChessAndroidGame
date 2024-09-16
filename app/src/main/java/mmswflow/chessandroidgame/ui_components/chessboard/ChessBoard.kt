package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.chess_game_classes.PieceColor

@Composable
fun ChessBoard(
    gameViewModel: ChessGameViewModel

){



    if(gameViewModel.chessBoard.value != null){

        val rowNotation = listOf("","1", "2", "3", "4", "5", "6", "7", "8","")
        val columnNotation = listOf("","a", "b", "c", "d", "e", "f", "g", "h","")
        val whoPlays = gameViewModel.whoPlays.value

        LazyVerticalGrid(columns = GridCells.Fixed(10)){


            //Display Row of Notations for the columns
            columnNotation.forEach{
                    cellNotation->
                    item{
                        BoardNotationCell(notation= cellNotation, zAngle = if(whoPlays!!.color == PieceColor.White) 0f else 180f)
                    }
            }

            //Display Column of Notations for rows

            //Display board matrix
            //Display Column of Notations for rows (again)
            //Display Row of Notations for the columns (again)

        }
    }

}
