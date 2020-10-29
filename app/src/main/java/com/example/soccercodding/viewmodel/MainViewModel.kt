package com.example.soccercodding.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccercodding.data.SoccerRepo
import com.example.soccercodding.model.SoccerData
import com.example.soccercodding.util.ApiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

// Injecting the viewModel which takes the Soccer Repo param for it's constructor
class MainViewModel @ViewModelInject constructor(
    private val soccerRepo: SoccerRepo
) : ViewModel() {

    // setting fixtures as a list of SoccerData as MutableLiveData wrapped in ApiState class
    private val _fixtures = MutableLiveData<ApiState<List<SoccerData>>>()
    // setting results as list of SoccerData as MutableLiveData wrapped in ApiState class
    private val _results = MutableLiveData<ApiState<List<SoccerData>>>()

    // getting fixtures as an observable list of Scodder data wrapped in an API state
    val fixtures: LiveData<ApiState<List<SoccerData>>>
        get() = _fixtures
    // getting results as an observable list of Scodder data wrapped in an API state
    val results: LiveData<ApiState<List<SoccerData>>>
        get() = _results

    // first block that will run when this class gets called
    init {
        getFixtures()
        getResults()
    }

    // coroutine launched as long as the viewModel is cleared
    private fun getFixtures() = viewModelScope.launch {
        // upstream handling of the values emitted from getFixtures() in repo (it will process every emitted value)
        soccerRepo.getFixtures().onEach { _fixtures.value = it }.launchIn(this)
    }

    // coroutine launched as long as the viewModel is cleared
    private fun getResults() = viewModelScope.launch {
        // upstream handling of the values emitted from getResults() in repo (it will process every emitted value)
        soccerRepo.getResults().onEach { _results.value = it }.launchIn(this)
    }
}