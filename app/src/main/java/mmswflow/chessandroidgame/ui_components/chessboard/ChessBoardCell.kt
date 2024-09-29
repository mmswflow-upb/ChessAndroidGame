package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.BoardCell
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.PiecePosition
import mmswflow.chessandroidgame.ui.theme.Black
import mmswflow.chessandroidgame.ui.theme.BrightRed
import mmswflow.chessandroidgame.ui.theme.LightGreen
import mmswflow.chessandroidgame.ui.theme.LightRed
import mmswflow.chessandroidgame.ui.theme.WarningOrange
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
@Composable
fun ChessBoardCell(
    cell: BoardCell,
    zAngle: Float,
    selectedChessPiece: MutableState<ChessPiece?>,
    iconPaddingMod: Modifier,
    playingColor: PieceColor,
    isVisibleToSelectedPiece: Boolean,
    onPieceMove: (PiecePosition) -> Unit,
    gameActive: Boolean = true,
    isCheckingPiece: Boolean,
    isSavingPiece: Boolean,
    kingUnderCheck: Boolean,
    isCheckMate: Boolean,
    isNormalCheck: Boolean
){
    val chessPiece = cell.occupyingPiece

    Box(
        modifier = Modifier
            .size(ChessBoardCellSize.value.dp)
            .border((cell.occupyingPiece.let{
                if(isVisibleToSelectedPiece && it != null && it.color == selectedChessPiece.value?.color) {
                    BorderStroke(color = LightGreen, width = SelectableBoardCellBorderStrokeWidth.value.dp)
                }else if(isVisibleToSelectedPiece && it != null && it.color != selectedChessPiece.value?.color){
                    BorderStroke(color= LightRed, width= SelectableBoardCellBorderStrokeWidth.value.dp)

                }else if(kingUnderCheck && isNormalCheck && !isCheckMate) {
                    BorderStroke(color= WarningOrange,width= SelectableBoardCellBorderStrokeWidth.value.dp)
                }else if(kingUnderCheck && isNormalCheck){
                    BorderStroke(color= BrightRed,width= SelectableBoardCellBorderStrokeWidth.value.dp)
                }else{
                    BorderStroke(width=0.dp,Black)

                }
            }),
                shape= RectangleShape
            )
            .clip(RectangleShape)
            .background(cell.cellColor)
            .clickable {

                if(!gameActive){
                    return@clickable
                }

                if (cell.occupyingPiece != null) {
                    if (chessPiece!!.color == playingColor) {

                        //Occupying piece is of the same color as the selected piece

                        if (selectedChessPiece.value == chessPiece) {
                            selectedChessPiece.value = null
                        } else {
                            selectedChessPiece.value = chessPiece
                        }
                    }else{
                        //Occupying piece is of the opposite color
                        //So the selected piece wishes to eat the occupying piece of this cell
                        onPieceMove(cell.position)
                    }
                } else {

                    onPieceMove(cell.position)
                }

            }
    ){

        if(cell.occupyingPiece != null){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier= Modifier.fillMaxSize()
            ) {
                ChessPieceIcon(
                    chessPiece = cell.occupyingPiece!!,
                    zAngle= zAngle,
                    selectedChessPiece= selectedChessPiece,
                    paddingMod= iconPaddingMod,
                    passedTint= if(isCheckingPiece && isNormalCheck && !isCheckMate) WarningOrange
                    else if(isCheckMate && isCheckingPiece && isNormalCheck) BrightRed
                    else if(isSavingPiece && isNormalCheck) LightGreen
                    else null
                )
            }
        }

        //When a player selects a piece, their paths should become visible, so we display green circles
        if(isVisibleToSelectedPiece && cell.occupyingPiece == null && gameActive){
            //If there's a check, we can only display the positions that would save the king for the selected piece
            //and that happens only if our selected piece is part of the ones that can actually save the king
            if((isNormalCheck && isSavingPiece && selectedChessPiece.value!!.listOfPositionsThatCanSaveKing.contains(cell.position)) || !isNormalCheck){

                Canvas(modifier = Modifier.fillMaxSize()){
                    drawCircle(
                        color= LightGreen,
                        radius= size.width /6f,
                        center= Offset(x= size.width/2f, y= size.height/2f)
                    )
                }
            }

        }
    }

}