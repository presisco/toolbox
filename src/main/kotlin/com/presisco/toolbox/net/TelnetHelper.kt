package com.presisco.toolbox.net

import com.presisco.toolbox.ArrayToolbox
import org.apache.commons.net.telnet.TelnetClient
import java.io.InputStream
import java.io.OutputStream

class TelnetHelper(
        private val host: String,
        private val port: Int = 23,
        private val code: String = "",
        private val bufferSize: Int = 4096,
        private val cmdIntervalMs: Long = 100
) {
    private val telnetClient = TelnetClient()
    private val keyboard: OutputStream
    private val screen: InputStream
    private val screenSpace = ByteArray(bufferSize)

    init {
        telnetClient.connect(host, port)
        keyboard = telnetClient.outputStream
        screen = telnetClient.inputStream
    }

    fun waitForMs(ms: Long) {
        Thread.sleep(ms)
    }

    fun read(expect: String = "\n"): String {
        var offset = 0
        val target = expect.toByteArray()
        while (true) {
            val count = screen.read(screenSpace, offset, bufferSize - offset)
            if (count > 0) {
                println("string till now: ${String(screenSpace, 0, offset + count)}")
                if (ArrayToolbox.contains(screenSpace, target, offset, offset + count - 1)) {
                    println("returning...")
                    return String(screenSpace, 0, offset + count)
                }
                offset += count
            }
        }
    }

    fun send(vararg cmdList: Pair<String, String>): String {
        var output = ""
        cmdList.forEach {
            keyboard.write("${it.first}\n".toByteArray())
            keyboard.flush()
            output = read(it.second)
            waitForMs(cmdIntervalMs)
        }
        return output
    }

    fun login(user: String, password: String): String {
        read("login:")
        waitForMs(cmdIntervalMs)
        send(Pair(user, "Password:"))
        waitForMs(cmdIntervalMs)
        return send(Pair(password, "$user@"))
    }

    fun close() {
        telnetClient.disconnect()
    }

}