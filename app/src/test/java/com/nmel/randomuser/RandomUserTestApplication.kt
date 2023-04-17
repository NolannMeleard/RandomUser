package com.nmel.randomuser

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RandomUserTestApplication : RandomUserApplication() {
    override fun onCreate() {
        // Allow call only the first time
        if (!onCreateCalled) {
            onCreateCalled = true
            super.onCreate()
        }
    }

    companion object {
        private var onCreateCalled = false
    }
}