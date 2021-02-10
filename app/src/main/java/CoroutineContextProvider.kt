package dev.applearrow.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutineContextProvider {
    open val Main: CoroutineDispatcher by lazy { Dispatchers.Main }
    open val IO: CoroutineDispatcher by lazy { Dispatchers.IO }
}