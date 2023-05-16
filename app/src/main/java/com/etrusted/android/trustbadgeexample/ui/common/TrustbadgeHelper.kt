package com.etrusted.android.trustbadgeexample.ui.common

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeState
import kotlinx.coroutines.delay

/**
 * Hides the badge after a the scroll state is not in position-zero
 * Applies a short delay before hiding the badge
 * Shows the badge when the scroll state is back in position-zero
 *
 * @param scrollState scroll state which the badge needs to react to
 * @param badgeState corresponding badge state
 */
@Composable
internal fun HideBadgeOnScroll(
    scrollState: ScrollState,
    badgeState: TrustbadgeState
) {
    if (scrollState.value == 0) {
        badgeState.show()
    } else {
        LaunchedEffect(null) {
            delay(1000)
            badgeState.hide()
        }
    }
}