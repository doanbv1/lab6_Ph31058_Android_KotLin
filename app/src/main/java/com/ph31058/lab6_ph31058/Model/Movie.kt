package com.ph31058.lab6_ph31058.Model

data class Movie(
    val title: String,
    val year: String,
    val posterUrl: String
){
    companion object {
        fun getSampleMovies() = listOf(
            Movie("The Godfather", "1972", "https://upload.wikimedia.org/wikipedia/vi/3/36/Mai_2024_poster.jpg"),
            Movie("The Godfather", "1972", "https://chieuphimquocgia.com.vn/_next/image?url=https%3A%2F%2Fapi.chieuphimquocgia.com.vn%2FContent%2FImages%2F0017596_0.jpg&w=256&q=75"),
            Movie("The Godfather", "1972", "https://chieuphimquocgia.com.vn/_next/image?url=https%3A%2F%2Fapi.chieuphimquocgia.com.vn%2FContent%2FImages%2F0017596_0.jpg&w=256&q=75"),
            Movie("The Godfather", "1972", "https://upload.wikimedia.org/wikipedia/vi/3/36/Mai_2024_poster.jpg"),
            Movie("The Godfather", "1972", "https://upload.wikimedia.org/wikipedia/vi/3/36/Mai_2024_poster.jpg"),
            Movie("The Dark Knight", "2008", "https://chieuphimquocgia.com.vn/_next/image?url=https%3A%2F%2Fapi.chieuphimquocgia.com.vn%2FContent%2FImages%2F0017521_0.jpg&w=256&q=75")
        )
    }
}
