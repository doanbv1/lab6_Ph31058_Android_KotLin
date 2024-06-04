package com.ph31058.lab6_ph31058

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ph31058.lab6_ph31058.Model.Movie
import com.ph31058.lab6_ph31058.component.ItemListRow
import com.ph31058.lab6_ph31058.component.MovieColumn
import com.ph31058.lab6_ph31058.component.MovieGrid
import com.ph31058.lab6_ph31058.component.MovieItem
import com.ph31058.lab6_ph31058.component.MovieRow
import com.ph31058.lab6_ph31058.ui.theme.Lab6_Ph31058Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6_Ph31058Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MovieScreen(movie = Movie.getSampleMovies())
//                    MovieScreen2(movies = Movie.getSampleMovies())
                    CinemaSeatBookingScreen( createTheaterSeating(
                        totalRows = 12,
                        totalSeatsPerRow = 9,
                        aislePositionInRow = 4,
                        aislePositionInColumn = 5
                    ), totalSeatsPerRow = 9
                    )
                }
            }
        }
    }
}

enum class ListType {
    ROW, COLUMN, GRID
}
@Composable
fun MovieScreen2(movies: List<Movie>) {
    var listType by remember { mutableStateOf(ListType.ROW) }
    Column {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { listType = ListType.ROW }) {
                Text("Row")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.COLUMN }) {
                Text("Column")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.GRID }) {
                Text("Grid")
            }
        }
        when (listType) {
            ListType.ROW -> MovieRow(movies = movies)
            ListType.COLUMN -> MovieColumn(movies)
            ListType.GRID -> MovieGrid(movies)
        }
    }

}


@Composable
fun MovieScreen (movie: List<Movie>) {
    LazyRow (
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(movie.size) {
          index -> ItemListRow(movie = movie[index])
      }
    }
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movie.size) {
                index -> ItemListRow(movie = movie[index])
        }
    }

}

//Bai3

data class Seat(var row: Char, val number: Int, var status: SeatStatus)
enum class SeatStatus { EMPTY, SELECTED, BOOKED, AISLE }

@Composable
fun SeatComposable(seat: Seat, clickable: Boolean = true) {
    var status by remember { mutableStateOf(seat.status) }
    val backgroundColor = when (status) {
        SeatStatus.EMPTY -> Color.LightGray.copy(alpha = 0.5f)
        SeatStatus.SELECTED -> Color(0xFFFFA500)
        SeatStatus.BOOKED -> Color.Gray
        SeatStatus.AISLE -> Color.Transparent
    }
    val borderModifier = if (status != SeatStatus.AISLE) {
        Modifier.border(

            BorderStroke(1.dp, Color.DarkGray.copy(alpha = 0.8f)), shape = RoundedCornerShape(8.dp)
        )
    } else Modifier
    Box(modifier = Modifier
        .padding(2.dp)
        .size(width = 35.dp, height = 30.dp)
        .then(borderModifier)
        .clip(RoundedCornerShape(8.dp))
        .background(backgroundColor)
        .padding(if (seat.status != SeatStatus.AISLE) 3.dp else 0.dp)
        .clickable(enabled = clickable && (status == SeatStatus.EMPTY || status == SeatStatus.SELECTED)) {
            status = if (status == SeatStatus.EMPTY) SeatStatus.SELECTED else SeatStatus.EMPTY
        },
        contentAlignment = Alignment.Center

    ){
        if (seat.status != SeatStatus.AISLE) {
            Text(
                text = "${seat.row}${seat.number}",
                style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold
            )
        }
    }

}

@Composable
fun CinemaSeatBookingScreen(seat: List<Seat>, totalSeatsPerRow: Int) {
    val textModifier = Modifier.padding(end = 16.dp, start =
    4.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)) {
        Text(text = "Screen",
            modifier = Modifier
                .padding(16.dp)
            ,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(totalSeatsPerRow)) {
            items(seat) {
                    item -> SeatComposable(seat = item)
            } }

        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val exampleEmptySeat = remember { Seat('X', 1, SeatStatus.EMPTY) }
            val exampleSelectedSeat = remember { Seat('Y', 1, SeatStatus.SELECTED) }
            val exampleBookedSeat = remember { Seat('Z', 1, SeatStatus.BOOKED) }
            SeatComposable(exampleEmptySeat, false)
            Text(
                text = "Available",
                style = MaterialTheme.typography.titleSmall, modifier = textModifier
            )
            SeatComposable(exampleSelectedSeat, false)
            Text(
                text = "Selected",
                style = MaterialTheme.typography.titleSmall, modifier = textModifier)}}}

fun createTheaterSeating(
    totalRows: Int,
    totalSeatsPerRow: Int,
    aislePositionInRow: Int,
    aislePositionInColumn: Int
): List<Seat> {
    val seats = mutableListOf<Seat>()
    for (rowIndex in 0 until totalRows) {
        for (seatIndex in 1..totalSeatsPerRow) {
            val adjustedRowIndex = if (rowIndex >=
                aislePositionInRow) rowIndex - 1 else rowIndex
            val adjustedSeatIndex =if (seatIndex >= aislePositionInColumn) seatIndex - 1 else seatIndex

            val isAisleRow = rowIndex == aislePositionInRow
            val isAisleColumn = seatIndex ==
                    aislePositionInColumn
            val status = when {
                isAisleRow || isAisleColumn -> SeatStatus.AISLE else -> if (Random.nextInt(0, 99) % 2 == 0)
                    SeatStatus.BOOKED else SeatStatus.EMPTY }

            seats.add(
                Seat(
                    row = 'A' + adjustedRowIndex,
                    number = adjustedSeatIndex,
                    status = status
                )
            )
        }
    }
    return seats
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Lab6_Ph31058Theme {
    MovieScreen(movie = Movie.getSampleMovies())
    }
}