package com.presisco.toolbox

import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

object CollectionToolbox {

    fun cloneAs(array: ArrayList<Any?>): ArrayList<Any?> {
        val newArray = ArrayList<Any?>(array.size)
        array.map { newArray.add(it) }
        return newArray
    }

    fun cloneAs(map: HashMap<String, Any?>): HashMap<String, Any?> {
        val newData = HashMap<String, Any?>()

        for ((key, value) in map) {
            if (value == null) {
                newData[key] = null
                continue
            }
            when (value) {
                is HashMap<*, *> -> newData[key] = cloneAs(value as HashMap<String, Any?>)
                is ArrayList<*> -> newData[key] = cloneAs(value as ArrayList<Any?>)
                else -> newData[key] = value
            }
        }

        return newData
    }

    fun buildMapFromLists(fieldList: List<String>, valueList: List<Any?>): HashMap<String, Any?> {
        val map = HashMap<String, Any?>(fieldList.size)
        fieldList.mapIndexed { index, field -> map[field] = valueList[index] }
        return map
    }
}