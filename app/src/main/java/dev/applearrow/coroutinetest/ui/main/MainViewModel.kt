package dev.applearrow.coroutinetest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.applearrow.coroutines.CoroutineContextProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()) :
    ViewModel() {

    val likesLiveData = MutableLiveData<String>()
    private var likeCount = 0

    private val ioContext: CoroutineContext = (contextProvider.IO)

    fun launchLikeCount() {
        viewModelScope.launch(ioContext) {
            val count = getLikeCountFromDb()
            likesLiveData.postValue("$likeCount likes")
        }
    }

    fun incrementLikes() {
        likeCount++
        likesLiveData.value = "$likeCount likes"
    }

    // Simulate we get data from database or network
    private suspend fun getLikeCountFromDb(): Int {
        delay(5_000)
        return likeCount
    }

}