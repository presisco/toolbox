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

    fun concat(data: Collection<*>, wrapper: String, seperator: String): String {
        if (data.isEmpty())
            return ""
        val sb = StringBuilder()
        val iterator = data.iterator()
        sb.append(wrapper)
        sb.append(iterator.next().toString())
        sb.append(wrapper)

        while (iterator.hasNext()) {
            sb.append(seperator)
            sb.append(wrapper)
            sb.append(iterator.next().toString())
            sb.append(wrapper)
        }

        return sb.toString()
    }

    fun servers2IPs(raw: Array<String>) = raw.map { it.substringBefore(':') }

    fun servers2Ports(raw: Array<String>) = raw.map { it.substringAfter(':') }

    fun servers2String(raw: Array<String>): String {
        val sb = StringBuilder()
        sb.append(raw[0])
        for (i in 1.until(raw.size)) {
            sb.append(',')
            sb.append(raw[i])
        }
        return sb.toString()
    }

}