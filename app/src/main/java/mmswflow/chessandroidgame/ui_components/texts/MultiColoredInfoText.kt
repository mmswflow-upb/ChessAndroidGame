package mmswflow.chessandroidgame.ui_components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle

@Composable
fun MultiColoredInfoText(

    modifier: Modifier = Modifier,
    colorsList: List<Color>,
    textList: List<String>
    ){


    Text(buildAnnotatedString {

        for(index in textList.indices){

            // Loop through each character in the string
            textList[index].forEachIndexed { i, char ->
                // Apply color to each character
                withStyle(style = SpanStyle(color = colorsList[index], fontStyle = FontStyle.Italic)) {
                    append(char)
                }
            }

        } },
        modifier= modifier
    )
}