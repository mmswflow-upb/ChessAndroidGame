package mmswflow.chessandroidgame.ui_components.selectable_options

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
import mmswflow.chessandroidgame.ui_components.texts.SmallInfoText

//The identifier is a picture or a big piece of text
//that gives the user an idea of what they're going to select
//without needing to read the description

@Composable
fun SelectableCard(
    identifier: @Composable () -> Unit,
    description: Int,
    actionOnSelection: () -> Unit,
    modifier: Modifier,
    scale: Float = 1f,
    minHeight: Int = SelectableCardMinHeight.value,
    maxHeight: Int = SelectableCardMaxHeight.value
){

    Card(
        shape= RoundedCornerShape(8.dp),
        modifier= modifier
            .padding(SelectableCardPadding.value.dp)
            .clip(RoundedCornerShape(SelectableCardRoundedCornerShapeSize.value.dp))
            .border(
                width = SelectableCardBorderWidth.value.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(SelectableCardRoundedCornerShapeSize.value.dp)
            )
            .graphicsLayer {
                scaleX = scale // Apply scaling based on focus
                scaleY = scale
            }
            .heightIn(min= minHeight.dp, max= maxHeight.dp )
            .clickable { actionOnSelection() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(SelectableCardElevation.value.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            identifier()

            SmallInfoText(text = stringResource(id= description))

        }

    }

}
