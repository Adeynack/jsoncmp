package com.github.adeynack.jsoncmp

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

fun main(args: Array<String>) = mainBody("jsoncmp") {
    Args(ArgParser(args)).let { parsed ->
        try {
            CliComparison().run(parsed.actualFilePath, parsed.expectedFilePath)
            System.exit(0)
        } catch (e: Exception) {
            println("JSON comparison failed")
            println(e.message)
            System.exit(1)
        }
    }
}
