package com.github.adeynack.jsoncmp

import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import java.io.File

class CliComparison {

    fun run(left: String?, right: String?) {
        if (left == null) throw IllegalArgumentException("No left file path is specified.")
        val leftFileContent = try {
            File(left).readText()
        } catch (e: Exception) {
            throw IllegalArgumentException("The left file could not be loaded: ${e.message}", e)
        }
        if (right == null) throw IllegalArgumentException("No right file path is specified.")
        val rightFileContent = try {
            File(right).readText()
        } catch (e: Exception) {
            throw IllegalArgumentException("The right file could not be loaded: ${e.message}", e)
        }
        val result = JSONCompare.compareJSON(leftFileContent, rightFileContent, JSONCompareMode.NON_EXTENSIBLE)
        println(
            if (result.passed()) "Contents are equivalent."
            else result.message
        )
    }

}
