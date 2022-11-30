package com.etrusted.android.trustbadge.library.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R

@Composable
fun ImageSeal(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.size(58.dp),
        painter = painterResource(id = R.drawable.ts_uncertified),
        contentDescription = stringResource(id = R.string.tbadge_seal_description)
    )
}

@Composable
fun ImageStore(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(0.5.dp, shape = CircleShape, color = Color.LightGray)
            .size(58.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize()
                .align(Alignment.Center)
                .padding(5.dp),
            painter = painterResource(id = R.drawable.ic_store),
            contentDescription = stringResource(id = R.string.tbadge_ic_store_description)
        )
    }
}

@Preview
@Composable
fun PreviewImageSeal() {
    Column {
        ImageSeal()
        ImageStore()
    }
}