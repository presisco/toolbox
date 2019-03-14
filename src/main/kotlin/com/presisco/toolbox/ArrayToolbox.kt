package com.presisco.toolbox

object ArrayToolbox {

    fun array2String(array: Array<*>): String {
        val sb = StringBuffer("[ ")
        for (item in array) {
            if (item is Array<*>) {
                sb.append(array2String(item)).append(", ")
            } else {
                sb.append(item.toString()).append(", ")
            }
        }
        return sb.append("]").toString()
    }

    fun anyArray2StringArray(anyArray: Array<*>) = Array(anyArray.size) { (anyArray[it] as String) }

    fun contains(source: ByteArray, target: ByteArray, start: Int = 0, end: Int = source.size - 1): Boolean {
        val srcStr = String(source, start, end)
        val targetStr = String(target)
        return srcStr.contains(targetStr)
    }
}