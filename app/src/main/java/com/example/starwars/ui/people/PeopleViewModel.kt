package com.example.starwars.ui.people

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.GetPeopleResponse
import com.example.starwars.model.data.Person
import com.example.starwars.model.mapper.toPerson
import com.example.starwars.repository.domain.StarWarsGetPeople
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleService: StarWarsGetPeople
) : ViewModel() {

    private val _people = MutableLiveData<List<Person>>()
    val people: LiveData<List<Person>> get() = _people

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchPeople()
    }

    private fun fetchPeople() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = getPeopleService.getPeople()
                val results = response.body()?.results
                _people.value = results?.mapNotNull { it?.toPerson() }
            } catch (e: Exception) {
                Log.e("PeopleViewModel", "Error fetching people: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
