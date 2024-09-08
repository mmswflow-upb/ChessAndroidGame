package mmswflow.chessandroidgame.ui_components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import mmswflow.chessandroidgame.ChessGameClasses.ChessPiece
import mmswflow.chessandroidgame.ChessGameClasses.PieceColor

@Composable
fun ChessPieceIconButton(chessPiece: ChessPiece, upsideDown: Boolean, onPieceSelect: (ChessPiece) -> Unit
    ){

    IconButton(
        onClick= {
            onPieceSelect(chessPiece)
        }
    ){
        Icon(painter= painterResource(chessPiece.icon),tint= if(chessPiece.color == PieceColor.White) Color.White else Color.DarkGray, contentDescription = null)
    }

}
