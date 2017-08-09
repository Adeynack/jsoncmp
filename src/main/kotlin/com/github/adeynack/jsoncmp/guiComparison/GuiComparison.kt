package com.github.adeynack.jsoncmp.guiComparison

import com.github.adeynack.jsoncmp.Args
import com.github.adeynack.jsoncmp.ComparisonBase
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class GuiComparison : ComparisonBase {

    override fun compare(arguments: Args) {
        val actual = loadFileContent(arguments.actualFilePath, true, "actual")
        val expected = loadFileContent(arguments.expectedFilePath, true, "expected")
        GuiComparisonFrame(actual, expected).apply {
            defaultCloseOperation = EXIT_ON_CLOSE
            isVisible = true
        }
    }

}
