/*
 * Created by Ali Kabiri on 30.11.2022.
 * Copyright (c) 2022 Trusted Shops AG
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

package com.etrusted.android.trustbadgeexample.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.etrusted.android.trustbadge.library.ui.theme.TsBlueAction
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey600
import com.etrusted.android.trustbadgeexample.ui.Screens

@Composable
internal fun RowScope.NavItem(
    screen: Screens,
    itemTextStyle: TextStyle,
    iconSize: Dp = 22.dp,
    selected: Boolean,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(screen.titleRes),
                style = itemTextStyle,
                softWrap = false,
            )
        },
        icon = {
            Icon(
                modifier = Modifier.height(iconSize),
                painter = painterResource(id = screen.iconRes),
                contentDescription = stringResource(screen.titleRes),
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                launchSingleTop = true
                popUpTo(route = screen.route)
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.TsBlueAction,
            selectedTextColor = MaterialTheme.colorScheme.TsBlueAction,
            unselectedIconColor = MaterialTheme.colorScheme.TsNeutralsGrey600,
            unselectedTextColor = MaterialTheme.colorScheme.TsNeutralsGrey600,
            indicatorColor = MaterialTheme.colorScheme.background,
        )
    )
}