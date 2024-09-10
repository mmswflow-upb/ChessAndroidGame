package mmswflow.chessandroidgame.ui_components.layout

import androidx.compose.material3.Icon

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import mmswflow.chessandroidgame.ui_components.text.ScreenTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    screenTitle: Int,
    navHost: NavHostController,
    navIcon: ImageVector
){

    TopAppBar(
        title = {
            ScreenTitleText(text= screenTitle)
        },
        colors= TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier= Modifier
            .clip(RoundedCornerShape(bottomStart=5.dp, bottomEnd= 5.dp))
            .shadow(shape= RoundedCornerShape(bottomStart=5.dp, bottomEnd = 5.dp), elevation = 5.dp),
        navigationIcon= {
            IconButton(onClick = { navHost.navigateUp() }) {
                Icon(
                    imageVector= navIcon,
                    contentDescription = null,
                    tint= MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}