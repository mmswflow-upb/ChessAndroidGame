package mmswflow.chessandroidgame.ui_components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
@Composable
fun MediumActionButton(
    clickAction: () -> Unit,
    buttonText: Int,
    modifier: Modifier
){
    Box(
        modifier= modifier
            .padding(MediumActionButtonBoxPadding.value.dp)
            .clip(RoundedCornerShape(MediumActionButtonRoundedCornerShapeSize.value.dp))
            .background(MaterialTheme.colorScheme.primary)
            .border(
                width= MediumActionButtonBorderWidth.value.dp,
                color= MaterialTheme.colorScheme.outline,
                shape= RoundedCornerShape(MediumActionButtonRoundedCornerShapeSize.value.dp)
            )
            .clickable { clickAction() },
            contentAlignment = Alignment.Center
    ){
        Button(
            modifier= Modifier.padding(MediumActionButtonPadding.value.dp),
            onClick= { clickAction() },
        ){
            Text(
                text= stringResource(id= buttonText),
                color= MaterialTheme.colorScheme.onPrimary,
                fontSize= MediumActionButtonTextFontSize.value.sp,
            )
        }
    }


}