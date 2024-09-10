package mmswflow.chessandroidgame.ui_components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece

@Composable
fun ChessPieceIconButton(chessPiece: ChessPiece, upsideDown: Boolean, onPieceSelect: (ChessPiece) -> Unit
    ){

    IconButton(
        onClick= {
            onPieceSelect(chessPiece)
        }
    ){
        Icon(painter= painterResource(chessPiece.icon), contentDescription = null)
    }

}
