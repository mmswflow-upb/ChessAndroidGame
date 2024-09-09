package mmswflow.chessandroidgame.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmswflow.chessandroidgame.ui_components.SizingValue.*
@Composable
fun SmallActionButton(
    clickAction: () -> Unit,
    buttonText: Int
){
    Box(
        modifier= Modifier
            .padding(SmallActionButtonBoxPadding.value.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .border(width= SmallActionButtonBorderWidth.value.dp, color= MaterialTheme.colorScheme.outline, shape= RoundedCornerShape(16.dp)
            )
    ){
        Button(
            modifier= Modifier.padding(SmallActionButtonPadding.value.dp),
            onClick= { clickAction() },
        ){

            Text(
                text= stringResource(id= buttonText),
                color= MaterialTheme.colorScheme.onPrimary,
                fontSize= SmallActionButtonTextSize.value.sp
            )
        }
    }


}