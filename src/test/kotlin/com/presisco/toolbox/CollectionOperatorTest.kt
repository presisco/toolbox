package com.presisco.toolbox

import com.presisco.toolbox.concurrent.CollectionOperator
import org.junit.Test

class CollectionOperatorTest {

    @Test
    fun sleepTest() {
        val data = arrayListOf(1, 2)
        val operator = CollectionOperator(4, data, {
            Thread.sleep(1000)
            it
        })

        val result = operator.execute()
        println(result.toString())
    }
}