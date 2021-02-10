# CoroutineTest
How to do unit test for coroutines. 

First of all read this very useful info about [kotlinx-coroutines-test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/)

I also found this article very usefull [Unit testing coroutines on android](https://www.ericdecanini.com/2020/04/06/unit-testing-coroutines-on-android/)

TLDR:

- Use runBlockingTest to test suspend functions in isolation and skip past delays
- To test functions that start their own coroutines like with launch, you want them to run on TestCoroutineDispatcher during tests
- To make the above easily possible, let your ViewModel take in a coroutine context provider of your making as a parameter, and create your ViewModel which a special test coroutine context provider during tests which changes all dispatchers to TestCoroutineDispatcher.
- Use the InstantTaskExecutorRule to make postValue and similar other-thread-posting-functions execute immediately.
