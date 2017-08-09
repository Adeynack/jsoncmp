package com.github.adeynack.jsoncmp

import com.github.adeynack.jsoncmp.guiComparison.GuiComparison
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

fun main(args: Array<String>) = mainBody("jsoncmp") {
    Args(ArgParser(args)).let { parsed ->

        try {
            if (parsed.gui) {
                GuiComparison().compare(parsed)
            } else {
                CliComparison().compare(parsed)
                System.exit(0)
            }
        } catch (e: Exception) {
            println("JSON comparison failed")
            println(e.message)
            System.exit(1)
        }
    }
}

