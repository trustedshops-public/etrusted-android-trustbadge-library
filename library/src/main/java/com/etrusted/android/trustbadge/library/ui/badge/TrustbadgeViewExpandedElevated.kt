package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.model.ProductGrade
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import com.etrusted.android.trustbadge.library.ui.theme.TsBadgeBg
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey50

@Composable
internal fun TrustbadgeViewExpandedElevated(
    state: TrustbadgeState,
    badgeContext: TrustbadgeContext,
    trustbadgeData: TrustbadgeData?,
    guarantee: TrustbadgeData.Shop.Guarantee?,
    productGrade: ProductGrade?,
) {
    ElevatedButton(
        modifier = Modifier
            .animateContentSize()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .height(66.dp)
            .defaultMinSize(minWidth = 66.dp),
        contentPadding = PaddingValues(start = 66.dp),
        onClick = {},
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.TsNeutralsGrey50),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.TsBadgeBg
        ),
    ) {
        ExpandedView(
            modifier = Modifier.align(Alignment.CenterVertically),
            state = state,
            badgeContext = badgeContext,
            rating = trustbadgeData?.shop?.rating,
            guaranteeAmount = guarantee?.maxProtectionAmount ?: "0",
            productRating = productGrade?.year?.rating,
        )
    }
}