package com.example.starwars.service

import com.example.starwars.model.GetPeopleResponse
import retrofit2.Response
import retrofit2.http.GET

interface PeopleService {
    @GET(PEOPLE)
    suspend fun getPeople(): Response<GetPeopleResponse>

    companion object {
        const val PEOPLE = "people"
    }
}