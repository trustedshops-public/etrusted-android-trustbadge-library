package com.etrusted.android.trustbadgeexample.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.etrusted.android.trustbadgeexample.R

/*
 * Created by Ali Kabiri on 23.02.2022
 * Copyright (c) 2022 Trusted Shops AG. All rights reserved.
 */

@Composable
fun HalfYellowBg(
    modifier: Modifier
) {
    Column(modifier = modifier
        .testTag("bottom_nav_bg")) {
        // fill half bottom of the column with yellow
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            painter = painterResource(id = R.drawable.bottom_bar_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}