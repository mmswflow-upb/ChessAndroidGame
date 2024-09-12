package mmswflow.chessandroidgame.ui_components.utility

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.ui_components.text.TextHeader

@Composable
fun GameEndDialog(
    gameViewModel: ChessGameViewModel,
    navHost: NavHostController
){
    Dialog(

        onDismissRequest = {gameViewModel.displayGameEndedDialog.value = true}
    ) {
        if(gameViewModel.winnerColor.value == PieceColor.White){

            TextHeader(text = stringResource(id = R.string.game_ending_dialog,
                R.string.white_color)
            )

        }else if(gameViewModel.winnerColor.value == PieceColor.Black){

            TextHeader(text = stringResource(id = R.string.game_ending_dialog,
                R.string.black_color)
            )
        }else{
            TextHeader(text = stringResource(id = R.string.game_ending_tie_dialog))

        }
    }
}