package com.presisco.toolbox.file

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.nio.charset.Charset

object FileToolbox {

    fun file2String(fileName: String) = String(file2bytes(fileName), Charset.forName("UTF-8"))

    fun string2File(fileName: String, data: String) {
        val writer = FileWriter(fileName)
        writer.write(data)
        writer.close()
    }

    fun file2bytes(fileName: String): ByteArray {
        val file = File(fileName)
        val buffer = ByteArray(file.length().toInt())
        val fis = FileInputStream(file)
        fis.read(buffer)
        fis.close()
        return buffer
    }

    fun bytes2file(fileName: String, data: ByteArray) {
        val file = File(fileName)
        val fos = FileOutputStream(file)
        fos.write(data)
        fos.close()
    }

    fun readFilenamesUnderDir(dirName: String, extension: String): MutableSet<String> {
        val nameSet = mutableSetOf<String>()
        val fileTree = File(dirName).walk()
        fileTree.maxDepth(1)
                .filter { it.isFile }.filter { it.extension == extension }
                .forEach { nameSet.add(it.name) }

        return nameSet
    }
}