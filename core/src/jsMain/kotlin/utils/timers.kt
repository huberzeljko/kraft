package de.peekandpoke.kraft.utils

import kotlinx.browser.window
import org.w3c.dom.Window


/**
 * Helper class implementing a debouncing timer.
 *
 * The block() passed to [schedule] will only be executed after [delayMs]
 * if no other call to [schedule] occurred in the meanwhile.
 */
class DebouncingTimer(private val delayMs: Int, private val delayFirstMs: Int = delayMs) {

    private var counter = 0
    private var timerId: Int? = null

    /**
     * Schedules [block] to be called after the timeout.
     */
    operator fun invoke(block: () -> Unit) {
        if (counter++ == 0) {
            schedule(delayFirstMs, block)
        } else {
            schedule(delayMs, block)
        }
    }

    /**
     * Schedules the next timeout.
     */
    private fun schedule(delay: Int, block: () -> Unit) {
        timerId?.let { clearTimeout(it) }

        timerId = setTimeout(delay) {
            timerId = null
            block()
        }
    }

    /**
     * Helper function for a nicer use of [Window.setTimeout] returning an Int.
     *
     * @return The timer id
     */
    private fun setTimeout(timeMs: Int, block: () -> Unit): Int {
        return window.setTimeout(block, timeMs)
    }

    /**
     * Helper function for a nicer use of [Window.clearTimeout].
     */
    private fun clearTimeout(timerId: Int) {
        window.clearTimeout(timerId)
    }
}
