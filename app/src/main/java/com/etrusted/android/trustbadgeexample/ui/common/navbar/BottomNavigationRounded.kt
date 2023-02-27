/*
 * Created by Ali Kabiri on 1.12.2022.
 * Copyright (c) 2022 Trusted Shops GmbH
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.etrusted.android.trustbadgeexample.ui.common.navbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.etrusted.android.trustbadge.library.ui.theme.*
import com.etrusted.android.trustbadgeexample.ui.Screens
import com.etrusted.android.trustbadgeexample.ui.common.NavItem

/*
 * Created by Ali Kabiri on 23.02.2022
 * Copyright (c) 2022 Trusted Shops GmbH. All rights reserved.
 */

@Composable
fun BottomNavigationRounded(
    modifier: Modifier,
    navController: NavHostController,
    bottomPadding: Dp = 16.dp,
    isExtraNarrowState: State<Boolean> = mutableStateOf(false),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val items = Screens::class.sealedSubclasses
        .map { it.objectInstance as Screens }
        .filter { it.isNav && it.order != 0}
        .sortedBy { it.order }

    Card(
        modifier = modifier.padding(bottom = bottomPadding, start = 24.dp, end = 24.dp, top = 0.dp),
        shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.TsNeutralsGrey50)
    ) {

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("bottom_nav"),
            tonalElevation = 0.dp,
        ) {

            val currentDestination = navBackStackEntry?.destination

            val itemTextStyle =
                if (isExtraNarrowState.value)
                    MaterialTheme.typography.mobileCaptionExtraNarrow
                else MaterialTheme.typography.mobileCaption

            items.forEach { screen ->
                val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                NavItem(
                    screen = screen,
                    itemTextStyle = itemTextStyle,
                    selected = selected,
                    navController = navController
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavRounded() {
    Surface(modifier = Modifier.background(color = Color.Black)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationRounded(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(80.dp),
                navController = rememberNavController()
            )
        }
    }
}