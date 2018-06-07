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
            val sleepInterval = interval - executeTime
            if (sleepInterval > 5) {
                Thread.sleep(sleepInterval)
            }
            stopWatch.start()
            task()
            stopWatch.stop()
            executeTime = stopWatch.durationFromStart()
        }
    }

}