package com.example.phonewalls.presentation.categories.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.phonewalls.presentation.categories.CategoriesState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CategoriesState())
    val state: State<CategoriesState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = CategoriesState(categories = result.data ?: emptyList())
                }

                is Result.Failure -> {
                    _state.value =
                        CategoriesState(error = result.message ?: "An unexpected error occurred")
                }

                is Result.Loading -> {
                    _state.value = CategoriesState(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}