package com.presisco.toolbox.net

import org.apache.commons.net.telnet.TelnetClient
import org.apache.commons.net.telnet.TelnetInputListener
import java.io.BufferedWriter
import java.io.InputStream

class TelnetHelper(
        private val host: String,
        private val port: Int = 23,
        private val code: String = "",
        private val readBufferSize: Int = 32768,
        private val cmdIntervalMs: Long = 100
) : TelnetInputListener {
    private val telnetClient = TelnetClient()
    private val keyboard: BufferedWriter
    private val screen: InputStream
    private val readBuffer = ByteArray(readBufferSize)
    private var bufferCursor = 0

    init {
        telnetClient.connect(host, port)
        telnetClient.registerInputListener(this)
        keyboard = telnetClient.outputStream.bufferedWriter()
        screen = telnetClient.inputStream
    }

    fun waitCmdInterval() {
        Thread.sleep(cmdIntervalMs)
    }

    override fun telnetInputAvailable() {
        readBuffer[bufferCursor++] = screen.read().toByte()
    }

    fun clearBuffer() {
        readBuffer.fill(0, 0, readBufferSize)
    }

    fun readRecording() = String(readBuffer, 0, bufferCursor)

    fun send(vararg cmdList: String) {
        cmdList.forEach {
            keyboard.write("$it\n")
            keyboard.flush()
            waitCmdInterval()
        }
    }

    fun close() {
        telnetClient.disconnect()
    }

}