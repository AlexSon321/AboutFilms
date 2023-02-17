package com.example.androiddevelopercourse.model

data class PopularItem(
    var page: Int,
    var results: ArrayList<Popular>,
    var backdrop_path: String,
    var title: String,
    var overview: String,
)
