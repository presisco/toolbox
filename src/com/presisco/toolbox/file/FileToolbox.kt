package com.presisco.toolbox.file

import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.nio.charset.Charset

object FileToolbox {

    fun file2String(fileName: String): String {
        val configFile = File(fileName)
        val buffer = ByteArray(configFile.length().toInt())
        val fis = FileInputStream(configFile)
        fis.read(buffer)
        fis.close()
        return String(buffer, Charset.forName("UTF-8"))
    }

    fun string2File(fileName: String, data: String) {
        val writer = FileWriter(fileName)
        writer.write(data)
        writer.close()
    }
}