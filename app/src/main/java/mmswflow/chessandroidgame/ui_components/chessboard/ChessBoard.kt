package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.PiecePosition

@Composable
fun ChessBoard(
    gameViewModel: ChessGameViewModel,
    modifier: Modifier,
    zAngle: Float
){

    if(gameViewModel.chessBoard.value != null){

        val rowNotation = listOf("1", "2", "3", "4", "5", "6", "7", "8")
        var columnNotation = listOf("","a", "b", "c", "d", "e", "f", "g", "h","")
        val playerInTurn = gameViewModel.playerInTurn.value

        val chessPieceIconPaddingMod= if(zAngle==0f) Modifier.padding(top=4.dp) else Modifier.padding(bottom=4.dp)

        val onPieceMove : (PiecePosition) -> Unit = {newPosition -> gameViewModel.movePiece(newPosition)}

        LazyVerticalGrid(
            modifier= modifier,
            columns = GridCells.Fixed(10)
        ){

            //Player 2 is white
            //Draw the board from top right corner of the matrix
            if(gameViewModel.player2.value!!.color == PieceColor.White){

                columnNotation = columnNotation.reversed()

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

                    for (column in 7 downTo 0){

                        val cell = gameViewModel.chessBoard.value!!.boardMatrix[row][column]

                        val isVisibleToSelectedPiece = gameViewModel.selectedChessPiece.value.let{
                            it?.protectsPosition(chessBoard = gameViewModel.chessBoard.value!! ,cell.position)
                        } ?: false

                        item{
                            ChessBoardCell(
                                cell= cell,
                                zAngle= zAngle,
                                selectedChessPiece= gameViewModel.selectedChessPiece,
                                paddingMod= chessPieceIconPaddingMod,
                                playingColor= playerInTurn!!.color,
                                isVisibleToSelectedPiece = isVisibleToSelectedPiece,
                                onPieceMove = onPieceMove
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
                columnNotation = columnNotation.reversed()

            }else if(gameViewModel.player1.value!!.color == PieceColor.White){


                //Player 1 is white
                //Draw the board from bottom left corner of the matrix (By Default)

                //Display Row of Notations for the columns
                columnNotation.forEach{
                        cellNotation->
                    item{
                        BoardNotationCell(notation= cellNotation, zAngle = zAngle)
                    }
                }

                //Display each row of the chessboard, with the row notation on each end

                for(row in 7 downTo 0){


                    item{
                        BoardNotationCell(notation= rowNotation[row], zAngle= zAngle)
                    }

                    for (column in 0..7){

                        val cell = gameViewModel.chessBoard.value!!.boardMatrix[row][column]
                        val isVisibleToSelectedPiece = gameViewModel.selectedChessPiece.value.let{
                            it?.protectsPosition(chessBoard = gameViewModel.chessBoard.value!! ,cell.position)
                        } ?: false

                        item{
                            ChessBoardCell(
                                cell= cell,
                                zAngle= zAngle,
                                selectedChessPiece= gameViewModel.selectedChessPiece,
                                paddingMod= chessPieceIconPaddingMod,
                                playingColor= playerInTurn!!.color,
                                isVisibleToSelectedPiece= isVisibleToSelectedPiece,
                                onPieceMove = onPieceMove
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

}
