package com.dicoding.academy.themealsapp.module.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.dicoding.academy.themealsapp.ui.theme.Shapes

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = "https://media.licdn.com/dms/image/C4D03AQFAOkPkati9QQ/profile-displayphoto-shrink_200_200/0/1607408289641?e=1681344000&v=beta&t=eVjIz0of0-_neDX60W36FZ0NT8mP2FF19sxp076S2hw",
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                CircularProgressIndicator()
            },
            modifier = Modifier
                .size(150.dp)
                .clip(Shapes.medium)
        )

        Text(
            text = "Gilang Ramadhan",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "gilangramadhan96.gr@gmail.com",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1
        )
    }
}