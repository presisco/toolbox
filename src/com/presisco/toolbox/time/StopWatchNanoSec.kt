package com.presisco.toolbox.time

class StopWatchNanoSec: StopWatch() {

    override fun now() = System.nanoTime()

}