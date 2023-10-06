package com.etrusted.android.trustbadgeexample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme

@Composable
fun TrustbadgeExampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    // using Trustbadge library's theme for simplicity
    // please prefer using your own theme in your app
    // and use TrustbadgeTheme for Trustbadge related components
    TrustbadgeTheme(
        darkTheme = darkTheme,
        content = content,
    )
}