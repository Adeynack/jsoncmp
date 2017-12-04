package com.github.adeynack.jsoncmp

import java.io.File

interface ComparisonBase {

    fun compare(arguments: Args)

    fun loadFileContent(filePath: String?, emptyIfNotSpecified: Boolean, fileDescription: String): String =
        if (filePath == null) {
            if (emptyIfNotSpecified) {
                ""
            } else {
                throw IllegalArgumentException("No '$fileDescription' file path is specified.")
            }
        } else try {
            File(filePath).readText()
        } catch (e: Exception) {
            throw IllegalArgumentException("The '$fileDescription' file could not be loaded: ${e.message}", e)
        }

}
