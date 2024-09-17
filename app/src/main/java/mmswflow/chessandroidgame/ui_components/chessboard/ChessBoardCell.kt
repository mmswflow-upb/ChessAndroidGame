package mmswflow.chessandroidgame.ui_components.chessboard

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.BoardCell
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.Player
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
@Composable
fun ChessBoardCell(
    cell: BoardCell,
    zAngle: Float,
    selectedChessPiece: MutableState<ChessPiece?>,
    paddingMod: Modifier,
    playingColor: PieceColor
){
    val chessPiece = cell.occupyingPiece
    Box(
        modifier = Modifier
            .size(ChessBoardCellSize.value.dp)
            .background(cell.cellColor)
            .clickable {
                if(cell.occupyingPiece != null){
                    if(chessPiece!!.color == playingColor){

                        if(selectedChessPiece.value == chessPiece){
                            selectedChessPiece.value = null
                        }else{
                            selectedChessPiece.value = chessPiece
                        }
                    }
                }else{
                    //TODO add behavior for piece to move to this position
                }

            }
    ){

        if(cell.occupyingPiece != null){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier= Modifier.fillMaxSize()
            ) {
                ChessPieceIconButton(
                    chessPiece = cell.occupyingPiece!!,
                    zAngle= zAngle,
                    selectedChessPiece= selectedChessPiece,
                    paddingMod= paddingMod,
                )
            }
        }
    }

}