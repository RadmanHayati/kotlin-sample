package com.radmanhayati.cafebazar.data.remote

data class ApiResult<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)