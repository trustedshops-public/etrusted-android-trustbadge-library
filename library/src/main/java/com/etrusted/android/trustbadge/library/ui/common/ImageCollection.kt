package com.etrusted.android.trustbadge.library.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.common.internal.ExcludeFromJacocoGeneratedReport


@Composable
internal fun ImageCircleGeneric(
    modifier: Modifier = Modifier,
    drawableId: Int,
    contentDescription: String,
) {
    Box(modifier = modifier.padding(4.dp)) {
        Box(
            modifier = Modifier.size(58.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                painter = painterResource(id = drawableId),
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun ImageCircleSeal(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.size(58.dp),
        painter = painterResource(id = R.drawable.ts_uncertified),
        contentDescription = stringResource(id = R.string.tbadge_seal_description)
    )
}

@Composable
fun ImageCircleBuyerProtection(
    modifier: Modifier = Modifier,
) {
    ImageCircleGeneric(
        modifier = modifier,
        drawableId = R.drawable.ic_buyer_protection,
        contentDescription = stringResource(id = R.string.tbadge_ic_buyer_protection_description)
    )
}

@Composable
fun ImageCircleShopIcon(
    modifier: Modifier = Modifier,
) {
    ImageCircleGeneric(
        modifier = modifier,
        drawableId = R.drawable.ic_store,
        contentDescription = stringResource(id = R.string.tbadge_ic_store_description)
    )
}

@Composable
fun ImageCircleProductIcon(
    modifier: Modifier = Modifier,
) {
    ImageCircleGeneric(
        modifier = modifier,
        drawableId = R.drawable.ic_store,
        contentDescription = stringResource(id = R.string.tbadge_ic_product_description)
    )
}

@ExcludeFromJacocoGeneratedReport
@Preview
@Composable
fun PreviewImageSeal() {
    Column {
        ImageCircleSeal()
        ImageCircleShopIcon()
        ImageCircleBuyerProtection()
    }
}