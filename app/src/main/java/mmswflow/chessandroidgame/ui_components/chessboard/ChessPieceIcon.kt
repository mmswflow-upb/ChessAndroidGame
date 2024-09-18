package mmswflow.chessandroidgame.ui_components.chessboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.ui.theme.Black
import mmswflow.chessandroidgame.ui.theme.LightBlue
import mmswflow.chessandroidgame.ui.theme.White
import mmswflow.chessandroidgame.ui_components.UISizingValue

@Composable
fun ChessPieceIconButton(
        chessPiece: ChessPiece,
        zAngle: Float,
        selectedChessPiece: MutableState<ChessPiece?>,
        paddingMod: Modifier,
    ){

    val tint: Color = if(selectedChessPiece.value != null && chessPiece == selectedChessPiece.value){

        LightBlue
    }else{

        if(chessPiece.color == PieceColor.White){
            White
        }else{
            Black
        }
    }


    Icon(
        modifier= paddingMod.graphicsLayer{ rotationZ= zAngle }.size(UISizingValue.ChessPieceIconSize.value.dp),
        painter= painterResource(chessPiece.icon),
        tint= tint,
        contentDescription = null
    )


}
