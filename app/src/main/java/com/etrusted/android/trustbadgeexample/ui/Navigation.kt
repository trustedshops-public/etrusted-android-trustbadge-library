/*
 * Created by Ali Kabiri on 30.11.2022.
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

package com.etrusted.android.trustbadgeexample.ui

import androidx.navigation.NavHostController
import com.etrusted.android.trustbadgeexample.R

sealed class Screens(
    val raw: String,
    val titleRes: Int,
    val iconRes: Int,
    val isNav: Boolean = false,
    val order: Int = 0, // 0 = no order
) {
    abstract val route: String

    object Home: Screens(
        raw = "home",
        titleRes = R.string.nav_t_home,
        iconRes = R.drawable.ic_home,
        isNav = true,
        order = 1,
    ) {
        override val route = this.raw
        fun navigate(navController: NavHostController) = navController.navigate(raw) {
            launchSingleTop = true
        }
    }

    object Products: Screens(
        raw = "products",
        titleRes = R.string.nav_t_products,
        iconRes = R.drawable.ic_products,
        isNav = true,
        order = 2,
    ) {
        override val route = this.raw
        fun navigate(navController: NavHostController) = navController.navigate(raw) {
            launchSingleTop = true
        }
    }

    object Checkout: Screens(
        raw = "checkout",
        titleRes = R.string.nav_t_checkout,
        iconRes = R.drawable.ic_checkout,
        isNav = true,
        order = 3,
    ) {
        override val route = this.raw
        fun navigate(navController: NavHostController) = navController.navigate(raw) {
            launchSingleTop = true
        }
    }

    object Profile: Screens(
        raw = "profile",
        titleRes = R.string.nav_t_profile,
        iconRes = R.drawable.ic_profile,
        isNav = true,
        order = 4,
    ) {
        override val route = this.raw
        fun navigate(navController: NavHostController) = navController.navigate(raw) {
            launchSingleTop = true
        }
    }
}