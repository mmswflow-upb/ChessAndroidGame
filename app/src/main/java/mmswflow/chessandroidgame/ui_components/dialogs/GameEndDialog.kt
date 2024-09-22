package mmswflow.chessandroidgame.ui_components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.ui.theme.Black
import mmswflow.chessandroidgame.ui.theme.BrightRed
import mmswflow.chessandroidgame.ui.theme.LightGreen
import mmswflow.chessandroidgame.ui.theme.LightYellow
import mmswflow.chessandroidgame.ui.theme.White
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
import mmswflow.chessandroidgame.ui_components.texts.LargeInfoText
import mmswflow.chessandroidgame.ui_components.texts.MediumInfoText
import mmswflow.chessandroidgame.ui_components.texts.MultiColoredInfoText

@Composable
fun GameEndDialog(
    gameViewModel: ChessGameViewModel,
    zAngle: Float
){
    val winner = gameViewModel.winnerPlayer.value
    val player1 = gameViewModel.player1.value!!
    val player2 = gameViewModel.player2.value!!

    var colorsLists : List<List<Color>>
    var textsLists : List<List<String>>

    Dialog(

        onDismissRequest = {gameViewModel.displayGameEndedDialog.value = false}
    ) {

        Card(
            modifier= Modifier
                .border(
                    border = BorderStroke(
                        width = GameEndDialogBorderStrokeWidth.value.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    shape = RoundedCornerShape(GameEndDialogRoundedCornerSize.value.dp)
                )
                .clip(RoundedCornerShape(GameEndDialogRoundedCornerSize.value.dp))
                .background(MaterialTheme.colorScheme.primary)
                .graphicsLayer {
                    rotationZ = zAngle
                }

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                //Check who's the winner
                when (winner) {
                    player1 -> {

                        //Player 1 won
                        textsLists = listOf(listOf(player1.name + " ", "+1"),listOf(player2.name + " ", "-1"))
                        colorsLists = listOf(listOf(White, LightGreen),listOf(White, BrightRed))
                    }

                    player2 -> {

                        //Player 2 won
                        textsLists = listOf(listOf(player1.name + " ", "-1"),listOf(player2.name + " ", "+1"))
                        colorsLists = listOf(listOf(White, BrightRed),listOf(White, LightGreen))
                    }
                    else -> {

                        //It's a draw
                        textsLists = listOf(listOf(player1.name + " ", "+1"),listOf(player2.name + " ", "+1"))
                        colorsLists = listOf(listOf(White, LightYellow),listOf(White, LightYellow))
                    }
                }

                Row{

                    LargeInfoText(text = "${winner!!.name} " + stringResource(id =  R.string.game_ending_dialog))
                }

                //Display changes in stats here
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //Player 1 side
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter= painterResource(id = R.drawable.king),
                            contentDescription = null,
                            tint= if(gameViewModel.player1.value!!.color == PieceColor.White) White else Black,
                            modifier= Modifier
                                .height(GameEndDialogIconSize.value.dp)
                                .padding(GameEndDialogIconPadding.value.dp)
                        )
                        MultiColoredInfoText(
                            colorsList = colorsLists[0],
                            textList = textsLists[0]
                        )
                    }

                    MediumInfoText(text = "VS")

                    //Player 2 side
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter= painterResource(id = R.drawable.king),
                            contentDescription = null,
                            tint= if(gameViewModel.player2.value!!.color == PieceColor.White) White else Black,
                            modifier= Modifier
                                .height(GameEndDialogIconSize.value.dp)
                                .padding(GameEndDialogIconPadding.value.dp)
                        )
                        MultiColoredInfoText(
                            colorsList = colorsLists[1],
                            textList = textsLists[1]
                        )
                    }
                }
            }
        }
    }
}