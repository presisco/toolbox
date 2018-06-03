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
        var executeTime = 0L
        val stopWatch = StopWatch()
        while (isRunning) {
            Thread.sleep(interval - executeTime)
            stopWatch.start()
            task()
            stopWatch.stop()
            executeTime = stopWatch.durationFromStart()
        }
    }

}