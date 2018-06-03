package com.presisco.toolbox

import org.junit.Test

class ArrayToolboxTest {

    @Test
    fun validateArray2String() {
        val array = arrayOf("a", "b", "c", "d")
        println(ArrayToolbox.array2String(array))
    }
}