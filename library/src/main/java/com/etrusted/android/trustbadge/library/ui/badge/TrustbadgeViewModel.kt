/*
 * Created by Ali Kabiri on 30.1.2023.
 * Copyright (c) 2023 Trusted Shops GmbH
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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etrusted.android.trustbadge.library.data.repository.ITrustbadgeRepository
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import com.etrusted.android.trustbadge.library.data.repository.TrustbadgeRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "TrustbadgeVM"

internal class TrustbadgeViewModel(
    private var scope: CoroutineScope? = null,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
    private val trustbadgeRepo: ITrustbadgeRepository = TrustbadgeRepository(),
): ViewModel() {

    init {
        // testScope is provided during tests otherwise uses default viewModelScope
        if (scope == null) scope = viewModelScope
    }

    private val _trustbadgeData = MutableStateFlow<TrustbadgeData?>(null)
    internal val trustbadgeData: StateFlow<TrustbadgeData?>
        get() = _trustbadgeData

    internal suspend fun fetchTrustbadgeData(
        tsId: String,
        channelId: String,
    ) = scope?.launch {
        withContext(dispatcherIO) {
            try {
                val resp = trustbadgeRepo.fetchTrustbadgeData(tsid = tsId, channelId = channelId)
                if (resp.isSuccess) {
                    withContext(dispatcherMain) {
                        _trustbadgeData.value = resp.getOrNull()
                    }
                } else {
                    Log.e(TAG, "error: ${resp.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "error: ${e.message}")
            }
        }
    }
}