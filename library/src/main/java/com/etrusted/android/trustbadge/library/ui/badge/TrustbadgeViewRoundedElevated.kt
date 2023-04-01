package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.theme.TsBadgeBg

@Composable
internal fun TrustbadgeViewRoundedElevated(
    state: TrustbadgeState,
    badgeContext: TrustbadgeContext
) {
    ElevatedButton(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 24.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {},
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.TsBadgeBg
        )
    ) {

        RoundedView(
            modifier = Modifier.testTag(TestTags.TrustbadgeDefault.raw),
            state = state, badgeContext = badgeContext
        )
    }
}