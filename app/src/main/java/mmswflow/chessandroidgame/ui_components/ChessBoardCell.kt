package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ChessGameClasses.ChessPiece
import mmswflow.chessandroidgame.data.BoardCell

@Composable
fun ChessBoardCell(
    cell: BoardCell,
    upsideDown: Boolean,
    onPieceSelect: (ChessPiece) -> Unit
){

    Box(modifier = Modifier
        .size(8.dp)
        .background(cell.cellColor), contentAlignment = Alignment.Center,){

            if(cell.occupyingPiece != null){
                ChessPieceIconButton(chessPiece = cell.occupyingPiece!!, upsideDown = upsideDown, onPieceSelect)
            }
    }

}