package com.presisco.toolbox.time

open class StopWatch {
    private var watchState = State.BLANK
    private var segmentState = State.BLANK

    private var startTime = 0L
    private var endTime = 0L
    private var splitTime = startTime
    private var segmentTime = 0L

    protected open fun now() = System.currentTimeMillis()

    fun start() {
        if(watchState == State.TIMING)
            throw IllegalStateException("StopWatch already running!")

        startTime = now()
        splitTime = startTime
        watchState = State.TIMING
    }

    fun startSegment() {
        if(segmentState == State.TIMING)
            throw IllegalStateException("StopWatch already running!")

        segmentTime = now()
        segmentState = State.TIMING
    }

    fun split(): Long {
        if(watchState != State.TIMING)
            throw IllegalStateException("StopWatch not started!")

        val duration = now() - splitTime
        splitTime = now()
        return duration
    }

    fun endSegment(): Long {
        if(watchState != State.TIMING)
            throw IllegalStateException("StopWatch not started!")

        val duration = now() - segmentTime
        segmentState = State.STOPPED
        segmentTime = now()
        return duration
    }

    fun durationFromStart(): Long {
        if(watchState != State.TIMING)
            throw IllegalStateException("StopWatch not started!")

        return now() - startTime
    }

    fun stop() {
        if(watchState != State.TIMING)
            throw IllegalStateException("StopWatch not started!")

        endTime = now()
        watchState = State.STOPPED
    }

    fun reset() {
        if(watchState == State.TIMING)
            throw IllegalStateException("StopWatch need to be stopped first!")

        startTime = 0
        splitTime = 0
        watchState = State.BLANK
    }

    companion object {
        enum class State{
            BLANK,
            TIMING,
            STOPPED
        }
    }
}