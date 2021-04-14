package com.example.domain.entities

data class PokeResponse(val count: Int, val next: String,val previous: String,val results: List<PokeResult>)

