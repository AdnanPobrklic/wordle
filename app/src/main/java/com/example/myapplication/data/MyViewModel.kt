package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.RetrofitClient
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val _wordData = MutableLiveData("No data")
    val wordData: LiveData<String> get() = _wordData

    init {
        fetchNewWord()
    }

    fun fetchNewWord() {
        viewModelScope.launch {
            getWord()
        }
    }

    private suspend fun getWord() {
        try {
            val isNetworkAvailable = RetrofitClient.isNetworkAvailable(getApplication())
            if (isNetworkAvailable) {
                val words = RetrofitClient.wordApiService.getWord()
                if (words.isNotEmpty()) {
                    _wordData.value = words[0]
                } else {
                    _wordData.value = "No data"
                }
            } else {
                _wordData.value = generateRandomWord()
            }
        } catch (e: Exception) {
            _wordData.value = "Error fetching data"
        }
    }

    fun checkWord(word: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitClient.dictionaryApiService.checkWord(word)
                callback(true)
            } catch (e: Exception) {
                callback(false)
            }
        }
    }

    private fun generateRandomWord(): String {
        val words = listOf(
            "debug", "image", "modem", "pixel", "stack",
            "apple", "brave", "crane", "dance", "eagle",
            "flame", "grape", "house", "input", "jolly",
            "kneel", "lemon", "mango", "nerve", "ocean",
            "pearl", "quack", "robot", "smile", "table"
        )

        return words.random()
    }
}
