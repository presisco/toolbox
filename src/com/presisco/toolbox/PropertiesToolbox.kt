package com.presisco.toolbox

import java.util.*

object PropertiesToolbox {
    fun props2Map(properties: Properties): Map<String, String> {
        val converted = mutableMapOf<String, String>()
        properties.forEach { key, value -> converted[key as String] = value as String }
        return converted
    }
}