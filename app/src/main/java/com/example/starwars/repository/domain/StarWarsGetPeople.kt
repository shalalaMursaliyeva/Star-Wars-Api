package com.example.starwars.repository.domain

import com.example.starwars.model.GetPeopleResponse
import com.example.starwars.service.PeopleService
import retrofit2.Response
import javax.inject.Inject

class  StarWarsGetPeople @Inject constructor(
    private val apiService: PeopleService
) {
    suspend fun getPeople(): Response<GetPeopleResponse> =  apiService.getPeople()
}