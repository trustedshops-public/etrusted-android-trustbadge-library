/*
 * Created by Ali Kabiri on 6.12.2022.
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

package com.etrusted.android.trustbadge.library.ui.badge

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeStateValue.DEFAULT
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeStateValue.EXPANDED
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeStateValue.EXPANDED_AS_CARD
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeStateValue.INVISIBLE
import kotlinx.coroutines.delay

enum class TrustbadgeStateValue {
    INVISIBLE,
    DEFAULT,
    EXPANDED,
    EXPANDED_AS_CARD,
}

@Composable
fun rememberTrustbadgeState(
    initialState: TrustbadgeStateValue = DEFAULT
): TrustbadgeState = rememberSaveable(saver = TrustbadgeState.Saver) {
    TrustbadgeState(
        currentState = initialState
    )
}

class TrustbadgeState(
    currentState: TrustbadgeStateValue = DEFAULT,
) {

    private var _currentState by mutableStateOf(currentState)

    /**
     * The currently selected state. This may not be the state which is
     * currently displayed on screen.
     *
     */
    var currentState: TrustbadgeStateValue
        get() = _currentState
        internal set(value) {
            if (value != _currentState) {
                _currentState = value
            }
        }

    fun expand() {
        currentState = when(currentState) {
            INVISIBLE -> DEFAULT
            DEFAULT -> EXPANDED
            EXPANDED -> EXPANDED
            EXPANDED_AS_CARD -> EXPANDED
        }
    }

    fun retract() {
        currentState = when (currentState) {
            INVISIBLE -> INVISIBLE
            DEFAULT -> INVISIBLE
            EXPANDED -> DEFAULT
            EXPANDED_AS_CARD -> EXPANDED
        }
    }

    /**
     * if the badge is invisible, set the state to default
     * otherwise do nothing as the badge is already visible
     */
    fun show() {
        if (currentState == INVISIBLE) {
            currentState = DEFAULT
        }
    }

    /**
     * Change the badge state from current state to INVISIBLE
     * if the current state is EXPANDED, retract it first.
     */
    suspend fun hide() {
        when (currentState) {
            INVISIBLE -> {
                // Intentionally left empty since Kotlin requires 'when' expression
                // to be exhaustive, it is necessary to add all branches (or 'else').
                // In this case, calling hide on visible state keeps it visible without changes.
            }
            DEFAULT -> {
                currentState = INVISIBLE
            }
            EXPANDED -> {
                currentState = DEFAULT
                delay(300)
                currentState = INVISIBLE
            }
            EXPANDED_AS_CARD -> {
                currentState = EXPANDED
                delay(300)
                currentState = DEFAULT
                delay(300)
                currentState = INVISIBLE
            }
        }
    }

    companion object {
        /**
         * The default [Saver] implementation for [TrustbadgeState].
         */
        val Saver = Saver<TrustbadgeState, TrustbadgeStateValue>(
            save = { it.currentState },
            restore = {
                TrustbadgeState(
                    currentState = DEFAULT,
                )
            }
        )
    }
}

/**
 * show the expanded state automatically, only if the context is not set to TRUSTMARK
 * When the badge is in circular form, only the TRUSTMARK state is present.
 *
 * @param context The current activity context to access resources for reading delay time
 * @param badgeContext The current badge context to check if the badge is Expandable
 */
internal suspend fun TrustbadgeState.present(
    context: Context,
    badgeContext: TrustbadgeContext
) {
    if (badgeContext.isExpandable) {
        delay(context.resources.getInteger(R.integer.tbadge_auto_present_delay).toLong())
        this.expand()
        delay(context.resources.getInteger(R.integer.tbadge_auto_hide_delay).toLong())
        this.retract()
    }
}
