package com.github.adeynack.jsoncmp.guiComparison

import com.github.adeynack.jsoncmp.createAction
import net.miginfocom.layout.AC
import net.miginfocom.layout.CC
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.WindowConstants

class GuiComparisonFrame(
    actualContent: String,
    expectedContent: String
) : JFrame("JSON Comparison") {

    private val actionCompare = createAction("Compare", "Compare the two JSON contents.", KeyEvent.VK_C, this::compare)
    private val actionQuit = createAction("Quit", "Quit this tool.", KeyEvent.VK_Q, this::dispose)

    private val txtActual = JTextArea(actualContent)
    private val txtExpected = JTextArea(expectedContent)

    private val txtResult = JTextArea().apply {
        isEditable = false
    }

    init {
        contentPane = JPanel(MigLayout(
            LC(),
            AC().shrink().gap()
                .grow().fill(),
            AC().grow().gap() // actual
                .grow().gap() // expected
                .grow().gap() // result
                .shrink() // buttons
        )).apply {

            add(JLabel("Actual"), CC().alignY("top"))
            add(JScrollPane(txtActual), CC().grow().wrap())

            add(JLabel("Expected"), CC().alignY("top"))
            add(JScrollPane(txtExpected), CC().grow().wrap())

            add(JLabel("Result"), CC().alignY("top"))
            add(JScrollPane(txtResult), CC().grow().wrap())

            add(
                JPanel(FlowLayout(FlowLayout.RIGHT)).apply {
                    add(JButton(actionCompare))
                    add(JButton(actionQuit))
                },
                CC().growX().spanX(2))

        }
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        pack()
        size = Dimension(500, 400)

        if (txtActual.text.isNotEmpty() && txtExpected.text.isNotEmpty()) {
            compare()
        }
    }

    private fun compare() {
        txtResult.text =
            try {
                val result = JSONCompare.compareJSON(txtExpected.text, txtActual.text, JSONCompareMode.NON_EXTENSIBLE)
                if (result.passed()) {
                    "Contents are equivalent."
                } else {
                    result.message
                }
            } catch (e: Exception) {
                "Unable to perform comparison:\n\n${e.message}"
            }
    }

}
