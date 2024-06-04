package com.ph31058.lab6_ph31058.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ph31058.lab6_ph31058.ListType
import com.ph31058.lab6_ph31058.Model.Movie
import com.ph31058.lab6_ph31058.R

@Composable
fun ItemListRow (movie : Movie){
    Card (
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column (
            modifier = Modifier
                .width(175.dp)
                .height(330.dp),

        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading),
                modifier = Modifier
                    .height(255.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
//                    .background(Color.Red)

                )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleSmall, maxLines = 2)
                Text(text = "Thời lượng : ${movie.year}", style = MaterialTheme.typography.bodySmall)

            }

        }

    }
}



    @Composable
    fun BoldValueText(label: String, value: String, style: TextStyle =
        MaterialTheme.typography.bodySmall
    ) {
        Text(buildAnnotatedString {
            append(label)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        }, style = style)
    }
    @Composable
    fun getItemSizeModifier(listType: ListType): Modifier {
        return when (listType) {
            ListType.ROW -> Modifier.width(175.dp)
            ListType.COLUMN -> Modifier
                .width(130.dp)
            ListType.GRID -> Modifier
                .fillMaxWidth()
        }
    }
@Composable
fun MovieItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor =
        Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation =
        6.dp),
    ) {
        Column(
            modifier = Modifier.then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {Text(
                text = movie.title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,

                overflow = TextOverflow.Ellipsis

            )
                BoldValueText(label = "Thời lượng: ", value =
                movie.title)
                BoldValueText(label = "Khởi chiếu: ", value =
                movie.year)
            }
        }
    }
}

@Composable
fun MovieItemColumn(movie: Movie, listType: ListType) {
    Card(
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor =
        Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation =
        6.dp),
    ) {
        Column(
            modifier = Modifier.then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {Text(
                text = movie.title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,

                overflow = TextOverflow.Ellipsis

            )
                BoldValueText(label = "Thời lượng: ", value =
                movie.title)
                BoldValueText(label = "Khởi chiếu: ", value =
                movie.year)
            }
        }
    }
}
@Composable
fun MovieColumnItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor =
        Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation =
        6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .then(getItemSizeModifier(listType))
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,

                    overflow = TextOverflow.Ellipsis

                )
                BoldValueText(label = "Thời lượng: ", value =
                movie.title)
                BoldValueText(label = "Khởi chiếu: ", value =
                movie.year)
                BoldValueText(label = "Thể loại: ", value =
                movie.year)
                Text(
                    text = "Tóm tắt nội dung",

                    style = MaterialTheme.typography.bodySmall,

                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom =
                    2.dp)
                )
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
    }
}
@Composable
fun MovieColumn(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(movies.size) { index ->
            MovieColumnItem(movie = movies[index], listType = ListType.COLUMN )
        }
    }
}
@Composable
fun MovieRow(movies: List<Movie>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.ROW)
        }
    }
}


@Composable
fun MovieGrid(movies: List<Movie>) {
    val gridState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.GRID)
        }
    }
}
