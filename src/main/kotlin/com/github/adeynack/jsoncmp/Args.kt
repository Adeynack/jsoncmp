package com.github.adeynack.jsoncmp

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class Args(parser: ArgParser) {

    val actualFilePath by parser.positional(
        "ACTUAL",
        help = "Path to the file considered as the 'actual' (aka 'left') in the comparison."
    ).default(null)

    val expectedFilePath by parser.positional(
        "EXPECTED",
        help = "Path to the file considered as the 'expected' (aka 'right') in the comparison."
    ).default(null)

    val gui by parser.flagging(
        "--gui", "-g",
        help = "Use a GUI front-end."
    )

}
