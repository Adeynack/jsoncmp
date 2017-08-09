package com.github.adeynack.jsoncmp

import java.io.File

abstract class ComparisonBase {

    abstract fun compare(arguments: Args)

    protected fun loadFileContent(filePath: String?, emptyIfNotSpecified: Boolean, fileDescription: String): String {
        if (filePath == null) {
            if (emptyIfNotSpecified) {
                return ""
            } else {
                throw IllegalArgumentException("No 'actual' file path is specified.")
            }
        }
        return try {
            File(filePath).readText()
        } catch (e: Exception) {
            throw IllegalArgumentException("The '$fileDescription' file could not be loaded: ${e.message}", e)
        }

    }

}
