package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece

@Composable
fun ChessPieceIconButton(chessPiece: ChessPiece, zAngle: Float, onPieceSelect: (ChessPiece) -> Unit
    ){

    IconButton(
        modifier= Modifier.graphicsLayer{ rotationZ= zAngle },
        onClick= {
            onPieceSelect(chessPiece)
        }
    ){
        Icon(painter= painterResource(chessPiece.icon), contentDescription = null)
    }

}
