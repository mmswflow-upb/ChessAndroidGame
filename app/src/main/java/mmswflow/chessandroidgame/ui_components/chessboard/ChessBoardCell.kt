package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.BoardCell
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
@Composable
fun ChessBoardCell(
    cell: BoardCell,
    whoPlays: PieceColor,
    selectedChessPiece: MutableState<ChessPiece?>
){

    Box(modifier = Modifier
        .size(ChessBoardCellSize.value.dp)
        .background(cell.cellColor), contentAlignment = Alignment.Center
    ){

        if(cell.occupyingPiece != null){
            ChessPieceIconButton(chessPiece = cell.occupyingPiece!!, zAngle= if(whoPlays == PieceColor.White) 0f else 180f , selectedChessPiece= selectedChessPiece)
        }
    }

}