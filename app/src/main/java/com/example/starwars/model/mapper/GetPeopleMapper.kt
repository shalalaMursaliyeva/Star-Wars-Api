package com.example.starwars.model.mapper

import com.example.starwars.model.GetPeopleResponse
import com.example.starwars.model.data.GetPeopleDomainData
import com.example.starwars.model.data.Person

fun GetPeopleResponse.toDomainModell(): GetPeopleDomainData {
    return GetPeopleDomainData(
        results = this.results?.map { it?.toDomainModel() },
        count = this.count,
        next = this.next,
        previous = this.previous
    )
}
fun GetPeopleResponse.Result.toDomainModel():GetPeopleDomainData.Result {
    return GetPeopleDomainData.Result(
        birthYear = this.birthYear,
        created = this.created,
        edited = this.edited,
        eyeColor = this.eyeColor,
        films = this.films,
        gender = this.gender,
        hairColor = this.hairColor,
        height = this.height,
        homeworld = this.homeworld,
        mass = this.mass,
        name = this.name,
        skinColor = this.skinColor,
        species = this.species,
        starships = this.starships,
        url = this.url,
        vehicles = this.vehicles

    )
}

fun GetPeopleResponse.Result.toPerson(): Person {
    return Person(
        birthYear = this.birthYear,
        created = this.created,
        edited = this.edited,
        eyeColor = this.eyeColor,
        films = this.films?.filterNotNull(), // Remove null entries
        gender = this.gender,
        hairColor = this.hairColor,
        height = this.height,
        homeworld = this.homeworld,
        mass = this.mass,
        name = this.name,
        skinColor = this.skinColor,
        species = this.species?.filterNotNull(),
        starships = this.starships?.filterNotNull(),
        url = this.url,
        vehicles = this.vehicles?.filterNotNull()
    )
}
