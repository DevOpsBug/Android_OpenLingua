package com.devopsbug.openlingua.ui.globalstate

import androidx.lifecycle.ViewModel
import com.devopsbug.openlingua.model.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OpenLinguaGlobalViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OpenLinguaGlobalState())
    val uiState: StateFlow<OpenLinguaGlobalState> = _uiState.asStateFlow()

    fun updateLanguage(newLanguage: Language) {
        _uiState.value = _uiState.value.copy(currentLanguage = newLanguage)
    }
}