package com.presisco.toolbox

import org.junit.Test
import kotlin.test.expect

class ArrayToolboxTest {

    @Test
    fun validateArray2String() {
        val array = arrayOf("a", "b", "c", "d")
        expect("[ a, b, c, d, ]") { ArrayToolbox.array2String(array) }
    }

    @Test
    fun byteArrayContain() {
        val source = "Lessons tearned en software te"
        val target = "software"
        with(ArrayToolbox) {
            expect(true) { contains(source.toByteArray(), target.toByteArray()) }
            expect(true) { contains(source.toByteArray(), target.toByteArray(), 19) }
            expect(false) { contains(source.toByteArray(), target.toByteArray(), 21) }
            expect(true) { contains("aaabb".toByteArray(), "aabb".toByteArray()) }
        }
    }
}