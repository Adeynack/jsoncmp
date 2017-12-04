package com.github.adeynack.jsoncmp

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action

fun createAction(
    name: String,
    shortDescription: String? = null,
    mnemonicKey: Int? = null,
    action: () -> Unit
) = object : AbstractAction(name) {

    init {
        shortDescription?.let {
            putValue(Action.SHORT_DESCRIPTION, it)
        }
        mnemonicKey?.let {
            putValue(Action.MNEMONIC_KEY, it)
        }
    }

    override fun actionPerformed(e: ActionEvent?) {
        action()
    }

}
