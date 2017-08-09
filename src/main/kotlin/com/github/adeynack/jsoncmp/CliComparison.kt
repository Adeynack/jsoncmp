package com.github.adeynack.jsoncmp

import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode

class CliComparison : ComparisonBase() {

    override fun compare(arguments: Args) {
        val actual = loadFileContent(arguments.actualFilePath, false, "actual")
        val expected = loadFileContent(arguments.expectedFilePath, false, "expected")
        val result = JSONCompare.compareJSON(expected, actual, JSONCompareMode.NON_EXTENSIBLE)
        println(
            if (result.passed()) "Contents are equivalent."
            else result.message
        )
    }

}
