package mmswflow.chessandroidgame.ui_components.selection_option

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
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
import mmswflow.chessandroidgame.ui_components.text.SmallInfoText

//The identifier is a picture or a big piece of text
//that gives the user an idea of what they're going to select
//without needing to read the description

@Composable
fun SelectionCard(
    identifier: @Composable () -> Unit,
    description: Int,
    actionOnSelection: () -> Unit,
    modifier: Modifier
){

    Card(
        shape= RoundedCornerShape(8.dp),
        modifier= modifier
            .padding(SelectionCardPadding.value.dp)
            .clip(RoundedCornerShape(SelectionCardRoundedCornerShapeSize.value.dp))
            .border(
                width = SelectionCardBorderWidth.value.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(SelectionCardRoundedCornerShapeSize.value.dp)
            )
            .heightIn(min= SelectionCardMinHeight.value.dp, max= SelectionCardMaxHeight.value.dp )
            .clickable { actionOnSelection() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(SelectionCardElevation.value.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            identifier()

            SmallInfoText(text = description)

        }

    }

}