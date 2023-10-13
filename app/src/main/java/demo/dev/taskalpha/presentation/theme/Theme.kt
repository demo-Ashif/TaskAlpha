package demo.dev.taskalpha.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF61A3F0),
    onPrimary = Color.White,
    secondary = Color(0xFF4CAF50),
    background = Color(0xFF121212), // Dark background for dark theme
    onBackground = Color(0xFFE0E0E0), // Lighter text color for dark theme
    surface = Color(0xFF1D1D1D), // Darker surface for list items in dark theme
    onSurface = Color(0xFFE0E0E0)  // Lighter text color for list items in dark theme
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF61A3F0),  // Blue-ish color for FAB
    onPrimary = Color.White,     // Text color on the FAB
    secondary = Color(0xFF4CAF50), // Green color for check marks
    background = Color(0xFFF5F5F5), // Light background color
    onBackground = Color(0xFF4A4A4A), // Text color on the background
    surface = Color(0xFFF5F5F5), // Surface color for list items
    onSurface = Color(0xFF4A4A4A)  // Text color on list items
)

@Composable
fun TaskAlphaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}