/*
 * Created by Ali Kabiri on 4.10.2023.
 * Copyright (c) 2023 Trusted Shops AG
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

package com.etrusted.android.trustbadge.library.ui.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

enum class TrustcardStateValue {
    CLASSIC_PROTECTION,
    PROTECTION_CONFIRMATION,
}

@Composable
internal fun rememberTrustcardState(
    initialState: TrustcardStateValue = TrustcardStateValue.CLASSIC_PROTECTION
): TrustcardState = rememberSaveable(saver = TrustcardState.Saver) {
    TrustcardState(
        currentState = initialState
    )
}

class TrustcardState(
    currentState: TrustcardStateValue = TrustcardStateValue.CLASSIC_PROTECTION,
) {

    private var _currentState by mutableStateOf(currentState)

    /**
     * The currently selected state. This may not be the state which is
     * currently displayed on screen.
     *
     */
    internal var currentState: TrustcardStateValue
        get() = _currentState
        internal set(value) {
            if (value != _currentState) {
                _currentState = value
            }
        }

    /**
     * Show the protection confirmation state.
     */
    internal fun showConfirmation() {
        currentState = TrustcardStateValue.PROTECTION_CONFIRMATION
    }

    internal companion object {
        /**
         * The default [Saver] implementation for [TrustcardState].
         */
        val Saver = Saver<TrustcardState, TrustcardStateValue>(
            save = { it.currentState },
            restore = {
                TrustcardState(
                    currentState = TrustcardStateValue.CLASSIC_PROTECTION,
                )
            }
        )
    }
}