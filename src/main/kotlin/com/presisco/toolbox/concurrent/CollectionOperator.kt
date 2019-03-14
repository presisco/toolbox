package com.presisco.toolbox.concurrent

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class CollectionOperator<IN, OUT>(
        private val threads: Int,
        dataSet: Collection<IN>,
        private val operation: (IN) -> OUT,
        private val update: (count: Int, data: IN) -> Unit = { _, _ -> }
) {
    private val dataQueue = ArrayBlockingQueue<IN>(dataSet.size)
    private val resultQueue = ArrayBlockingQueue<OUT>(dataSet.size)

    init {
        dataQueue.addAll(dataSet)
    }

    fun execute(): List<OUT> {
        val latch = CountDownLatch(threads)
        val executor = Executors.newFixedThreadPool(threads)

        for (index in 1..threads) {
            executor.execute {
                var data = dataQueue.poll()
                while (data != null) {
                    resultQueue.put(operation(data))
                    update(resultQueue.size, data)
                    data = dataQueue.poll()
                }
                latch.countDown()
            }
        }

        latch.await()
        return resultQueue.toList()
    }

}