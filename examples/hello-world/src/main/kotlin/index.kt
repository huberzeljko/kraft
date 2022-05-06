package de.peekandpoke.kraft.examples.helloworld

import de.peekandpoke.kraft.vdom.preact.PreactVDomEngine
import de.peekandpoke.ultra.semanticui.ui
import kotlinx.browser.document
import kotlinx.html.h1
import kotlinx.html.h2
import org.w3c.dom.HTMLElement

fun main() {
    val mountPoint = document.getElementById("spa") as HTMLElement

    PreactVDomEngine(mountPoint) {
        h1 { +"Hello World!" }

        h2 { +"First Counter" }

        CounterComponent(0)

        h2 { +"Another Counter" }

        CounterComponent(10)

        ui.divider {}

        ui.header H2 { +"Playground ..." }
    }
}
