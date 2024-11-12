package com.example.bricksGame.components.gameMeny.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    var titleDescription: MutableLiveData<String> = MutableLiveData("Home")
    var count: MutableLiveData<Int> = MutableLiveData(0)

    fun setNameOnHomeScreen(description: String) {
        titleDescription.value = description.toString()
        println(titleDescription.value)
    }

    fun increase(number: Int) {
        count.value = count.value?.plus(number)
    }
}


