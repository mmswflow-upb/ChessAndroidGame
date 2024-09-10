package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.ui_components.SizingValue.*

//The identifier is a picture or a big piece of text
//that gives the user an idea of what they're going to select
//without needing to read the description

@Composable
fun SelectionCard(
    identifier: @Composable () -> Unit,
    description: Int,
    actionOnSelection: () -> Unit
){

    Card(
        shape= RoundedCornerShape(8.dp),
        modifier= Modifier
            .fillMaxWidth()
            .padding(SelectionCardPadding.value.dp)
            .clip(RoundedCornerShape(SelectionCardRoundedCornerShapeSize.value.dp))
            .background(MaterialTheme.colorScheme.primary)
            .border(
                width = SelectionCardBorderWidth.value.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(SelectionCardRoundedCornerShapeSize.value.dp)
            )
            .clickable { actionOnSelection() }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            identifier()

            MediumInfoText(text = description)

        }

    }




}
