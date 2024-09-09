package mmswflow.chessandroidgame.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(

    //Buttons
    primary = DarkGray,
    onPrimary = Blue,

    //Dialogs
    primaryContainer= DarkPurple,
    onPrimaryContainer= White,


    // Info
    secondary = DarkGray,
    onSecondary = White,

    //Timers
    secondaryContainer = LightGray,
    onSecondaryContainer = LightBlue,

    //Chess Pieces & Board
    tertiary = Blue,
    onTertiary = Black,
    tertiaryContainer = LightBlue,
    onTertiaryContainer = DarkGray,

    //Dangerous Dialogs
    error= BrightRed,
    onError = White,
    errorContainer = LightRed,
    onErrorContainer = White,

    background= Black,


    surface= Black,
    onSurface = White,

    outline=Blue,
    outlineVariant= White
)

private val LightColorScheme = lightColorScheme(

    //Buttons
    primary = LightBlue,
    onPrimary = White,

    //Dialogs
    primaryContainer= LightGreen,
    onPrimaryContainer= White,

    secondary = LightPurple,
    onSecondary = White,

    //Timers
    secondaryContainer = DarkPurple,
    onSecondaryContainer = White,

    //Chess Pieces & Board
    tertiary = WarningOrange,
    onTertiary = Black,
    tertiaryContainer = Black,
    onTertiaryContainer = White,

    //Dangerous Dialogs
    error= White,
    onError = BrightRed,
    errorContainer = White,
    onErrorContainer = LightRed,

    background= White,

    surface= White,
    onSurface = Black,

    outline=Black,
    outlineVariant= DarkGray
)

@Composable
fun ChessAndroidGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme;
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}