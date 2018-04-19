package com.presisco.toolbox

object StringToolbox {

    fun concat(data: Any, count: Int, seperator: String): String {
        if (count < 1)
            return ""
        val sb = StringBuilder()
        sb.append(data.toString())
        if (count == 1)
            return sb.toString()
        for (i in 2..count) {
            sb.append(seperator)
            sb.append(data.toString())
        }
        return sb.toString()
    }

    fun concat(data: Collection<*>, seperator: String): String {
        if (data.isEmpty())
            return ""
        val sb = StringBuilder()
        val iterator = data.iterator()
        sb.append(iterator.next().toString())

        while (iterator.hasNext()) {
            sb.append(seperator)
            sb.append(iterator.next().toString())
        }

        return sb.toString()
    }

    fun concat(data: Array<*>, seperator: String): String {
        if (data.isEmpty())
            return ""
        val sb = StringBuilder()
        val iterator = data.iterator()
        sb.append(iterator.next().toString())

        while (iterator.hasNext()) {
            sb.append(seperator)
            sb.append(iterator.next().toString())
        }

        return sb.toString()
    }

    fun servers2IPs(raws: Array<String>) = raws.map { it.substringBefore(':') }

    fun servers2Ports(raws: Array<String>) = raws.map { it.substringAfter(':') }

    fun servers2String(raws: Array<String>): String {
        val sb = StringBuilder()
        sb.append(raws[0])
        for (i in 1.until(raws.size)) {
            sb.append(',')
            sb.append(raws[i])
        }
        return sb.toString()
    }

}