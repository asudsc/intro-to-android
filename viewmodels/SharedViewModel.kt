package com.asudsc.recipeapplication.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel() : ViewModel() {
    val searchTextState: MutableState<String> =
        mutableStateOf("")
}