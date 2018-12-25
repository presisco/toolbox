package com.presisco.toolbox

import org.junit.Test
import kotlin.test.expect

class ArrayToolboxTest {

    @Test
    fun validateArray2String() {
        val array = arrayOf("a", "b", "c", "d")
        println(ArrayToolbox.array2String(array))
    }

    @Test
    fun byteArrayContain() {
        val source = "Lessons tearned en software te"
        val target = "software"
        expect(true) { ArrayToolbox.contains(source.toByteArray(), target.toByteArray()) }
        expect(true) { ArrayToolbox.contains(source.toByteArray(), target.toByteArray(), 19) }
        expect(false) { ArrayToolbox.contains(source.toByteArray(), target.toByteArray(), 21) }
    }
}