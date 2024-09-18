package mmswflow.chessandroidgame.chess_game_classes

import android.util.Log
import kotlin.random.Random

/*
*   Check if the piece is still blocked due to a potential check
*  Check first if the move is legal
*  If it's legal and there's a piece in the new position call the eatpiece method from the gameRules class
*
*  When trying to check if a move would free the king from a check, simulate what would happen if it moved then revert if it still keeps the king in check
*
*/

//To check for a checkmate, we need to find the number of pieces checking the king
//if theres only one piece checking the king then we have to either eat the piece or
// move the king or cover the king with a piece
//If there's more than one piece, then we have to either cover the king
// (which could include eating a piece on a specific square to cover the king), or move the king
// (which potentially includes eating a piece)

//If none of these options are available then its a checkmate


fun chooseRandomColorOffline() : Pair<PieceColor,PieceColor>{

    val randomBinary = if(Math.random() < 0.5) 0 else 1

    var firstColor = PieceColor.White
    var secondColor = PieceColor.Black

    if(randomBinary == 0){
        firstColor = PieceColor.Black
        secondColor = PieceColor.White
    }


    return Pair(firstColor, secondColor)
}

fun eatPiece(){

}