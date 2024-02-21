package com.kaiba.hiltrealm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiba.hiltrealm.data.AppModuleRepo
import com.kaiba.hiltrealm.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(val repo : AppModuleRepo) : ViewModel() {
    var size = mutableStateOf(repo.getData().size)
    var items = mutableStateOf(repo.getData())
    var text = mutableStateOf("")

    suspend fun add(text: String) {
        // Add new data in a background thread
        viewModelScope.launch {
            repo.addData(text)
            getData()
        }
        // Update the data state after adding new data

    }
    suspend fun delete(){
        viewModelScope.launch {
            repo.deleteData()
            getData()
        }
    }

    fun getData() {
        // Fetch the data from the repo synchronously

        // Update the data state with the new size
        size.value = repo.getData().size
        items.value = repo.getData()
    }
}