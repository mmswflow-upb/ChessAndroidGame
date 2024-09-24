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


/*
* 1. Check whether the king is being checked
* 2. Check if move is legal
* 3. Check if moved piece gets its own king under check
* 4. Check if moved piece puts in check the enemy's king
* 5. If moved piece puts the enemy king in a position of check, then we also look for a checkmate
*
* To perform step 1:
* 2 lists exist to keep track of pieces that are checking the enemy kings, if for the current player its king is not being checked by
* enemy pieces then we can move on to step 2, otherwise we have to tell the user which piece can be moved and save the king from the list
* of pieces that can save it
*
* To perform step 2: Call the isLegal function on the moved piece
*
* To perform step 3:
* 1. Simulate the move on a test board
* 2. Go through all enemy pieces and see which one can see the king
* 3. If there's none, then we can perform the move, otherwise we can't
*
* To perform step 4:
* Same as step 3 but for enemy pieces, but we store the pieces that are
* checking the enemy king inside a list
*
* To perform step 5:
* 1. n = number of enemy pieces checking the king
* 2. If n is 1, we can either move the king or cover it with another piece or eat the enemy piece
* 3. If n is bigger than 1, we can only cover the king or move it
* (which could include eating an enemy piece but not necessarily the one checking the king)
* 4. We have to loop through all our pieces and see for each one of them whether any of their possible moves could save
* the king by simulating over and over
* */
