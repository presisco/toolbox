package com.presisco.toolbox.net

import org.apache.commons.net.telnet.TelnetClient
import java.io.BufferedWriter
import java.io.InputStream

class TelnetHelper(
        private val host: String,
        private val port: Int = 23,
        private val code: String = "",
        private val cmdIntervalMs: Long = 100
) {
    private val telnetClient = TelnetClient()
    private val keyboard: BufferedWriter
    private val screen: InputStream

    init {
        telnetClient.connect(host, port)
        keyboard = telnetClient.outputStream.bufferedWriter()
        screen = telnetClient.inputStream
    }

    fun waitCmdInterval() {
        Thread.sleep(cmdIntervalMs)
    }

    fun read(expect: String): String {
        val screenBuffer = StringBuffer()
        while (true) {
            val available = screen.available()
            val buffer = ByteArray(available)
            screen.read(buffer)
            val newText = String(buffer)
            screenBuffer.append(newText)

            if (screenBuffer.contains(expect)) {
                return screenBuffer.toString()
            }
        }
    }

    fun send(vararg cmdList: Pair<String, String>): String {
        val sb = StringBuilder()
        cmdList.forEach {
            keyboard.write("${it.first}\n")
            keyboard.flush()
            sb.append(read(it.second))
            waitCmdInterval()
        }
        return sb.toString()
    }

    fun close() {
        telnetClient.disconnect()
    }

}