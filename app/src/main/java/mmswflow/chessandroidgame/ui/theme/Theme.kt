package mmswflow.chessandroidgame.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    onPrimary = Blue,
    primaryContainer= DarkPurple,
    onPrimaryContainer= White,

    secondary = DarkGray,
    onSecondary = White,
    secondaryContainer = LightGray,
    onSecondaryContainer = LightBlue,

    tertiary = Blue,
    onTertiary = Black,
    tertiaryContainer = LightBlue,
    onTertiaryContainer = DarkGray,

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

    primary = LightBlue,
    onPrimary = White,
    primaryContainer= LightGreen,
    onPrimaryContainer= White,

    secondary = LightPurple,
    onSecondary = White,
    secondaryContainer = DarkPurple,
    onSecondaryContainer = White,

    tertiary = WarningOrange,
    onTertiary = Black,
    tertiaryContainer = Black,
    onTertiaryContainer = White,

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
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
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