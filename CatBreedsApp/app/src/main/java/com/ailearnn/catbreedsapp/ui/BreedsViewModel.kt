package com.ailearnn.catbreedsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailearnn.catbreedsapp.data.Breed
import com.ailearnn.catbreedsapp.data.CatService
import com.ailearnn.catbreedsapp.data.ICatService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedsViewModel(private val catService: ICatService = CatService.service) : ViewModel() {
    private val _breedsLiveData = MutableStateFlow(BreedsResult())
    val breedsLiveData: StateFlow<BreedsResult> = _breedsLiveData.asStateFlow()

    //var breedsLiveData = MutableLiveData<BreedsResult>()
    //    private set

    fun getBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResult = catService.getBreeds()

            //Log.d(BreedsViewModel::class.simpleName, "$apiResult")

            if (apiResult.isEmpty()) {
                _breedsLiveData.update { currentData ->
                    currentData.copy(error = "Empty list of breeds retrieved from API")
                }
            } else {
                _breedsLiveData.update { currentData ->
                    currentData.copy(data = apiResult)
                }
            }
        }
    }

    data class BreedsResult(val data: List<Breed>? = emptyList(), val error: String? = null)
}
