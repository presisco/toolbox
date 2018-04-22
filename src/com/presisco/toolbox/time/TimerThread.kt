package com.presisco.toolbox.time

class TimerThread(
        private val interval: Long,
        private val task: () -> Unit
) : Thread() {
    private var isRunning = true

    fun stopTimer() {
        isRunning = false
    }

    override fun run() {
        while (isRunning) {
            Thread.sleep(interval)
            task()
        }
    }

}