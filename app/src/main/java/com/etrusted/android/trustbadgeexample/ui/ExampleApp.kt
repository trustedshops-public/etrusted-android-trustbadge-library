/*
 * Created by Ali Kabiri on 29.11.2022.
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

package com.etrusted.android.trustbadgeexample.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.etrusted.android.trustbadgeexample.ui.checkout.CheckoutScreen
import com.etrusted.android.trustbadgeexample.ui.common.navbar.BottomNavBar
import com.etrusted.android.trustbadgeexample.ui.home.HomeScreen
import com.etrusted.android.trustbadgeexample.ui.products.ProductScreen
import com.etrusted.android.trustbadgeexample.ui.profile.ProfileScreen
import com.etrusted.android.trustbadgeexample.ui.theme.TrustbadgeExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExampleApp() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Screens.Home.route) {
                composable(Screens.Home.route) {
                    HomeScreen()
                }
                composable(Screens.Checkout.route) {
                    CheckoutScreen()
                }
                composable(Screens.Products.route) {
                    ProductScreen()
                }
                composable(Screens.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ExampleAppPreview() {
    TrustbadgeExampleTheme {
        ExampleApp()
    }
}