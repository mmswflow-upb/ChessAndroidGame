package mmswflow.chessandroidgame.ui_components.screens_utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.chess_game_classes.GameMode
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.Player
import mmswflow.chessandroidgame.ui.theme.Black
import mmswflow.chessandroidgame.ui.theme.BrightRed
import mmswflow.chessandroidgame.ui.theme.LightGreen
import mmswflow.chessandroidgame.ui.theme.LightYellow
import mmswflow.chessandroidgame.ui.theme.White
import mmswflow.chessandroidgame.ui_components.texts.MultiColoredInfoText
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
import mmswflow.chessandroidgame.ui_components.texts.ScreenTitleText

@Composable
fun PlayerInfoCard(
    player: Player,
    gameMode: GameMode
){

    Box(
        modifier= Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = PlayerInfoCardBorderStroke.value.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                shape = RoundedCornerShape(PlayerInfoCardRoundedCornerShapeSize.value.dp)
            )
            .clip(shape = RoundedCornerShape(PlayerInfoCardRoundedCornerShapeSize.value.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier= Modifier.weight(2f)
            ){
                Icon(
                    painter= painterResource(id = R.drawable.king),
                    contentDescription = null,
                    modifier= Modifier
                        .height(PlayerInfoCardIconSize.value.dp)
                        .padding(PlayerInfoCardIconPadding.value.dp),
                    tint= if(player.color == PieceColor.White) White else Black,
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {

                    ScreenTitleText(text = player.name)
                    MultiColoredInfoText(
                        colorsList = listOf(LightGreen,White, BrightRed,White, LightYellow),
                        textList = listOf("${player.wins}", " | ", "${player.losses}"," | ","${player.draws}")
                    )
                }
            }

            TimerUI(
                time = player.remainingTime,
                maxTime = gameMode.timeLimit,
                active = player.active,
                modifier= Modifier.weight(1f)
            )

        }

    }


}