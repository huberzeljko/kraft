package de.peekandpoke.kraft.store

/**
 * Base class for all stream wrappers
 */
class StreamCombinator<FIRST, SECOND, RESULT>(
    /** The first wrapped stream */
    private val first: Stream<FIRST>,
    /** The second wrapped stream */
    private val second: Stream<SECOND>,
    /** The function that combines both */
    private val combine: (FIRST, SECOND) -> RESULT,
) : Stream<RESULT> {

    /** Unsubscribe function for the subscription on the first wrapped stream */
    private var firstUnsubscribe: Unsubscribe? = null

    /** Unsubscribe function for the subscription on the second wrapped stream */
    private var secondUnsubscribe: Unsubscribe? = null

    /** All subscriptions to this stream */
    private val subscriptions = mutableSetOf<StreamHandler<RESULT>>()

    override fun invoke(): RESULT {
        return combine(first(), second())
    }

    /**
     * Adds a subscription to the stream.
     *
     * On subscribing the subscription is immediately called with the current value.
     *
     * Returns an unsubscribe function. Calling this function unsubscribes from the stream.
     *
     * NOTICE: It is the callers obligation to unsubscribe from the stream.
     */
    override fun subscribeToStream(sub: StreamHandler<RESULT>): Unsubscribe {
        this.subscriptions.add(sub)

        // Subscribe to the first wrapped stream if necessary
        if (firstUnsubscribe == null) {
            // console.log("Subscribing to wrapped")
            firstUnsubscribe = first.subscribeToStream {
                handleInComing()
            }
        }

        // Subscribe to the second wrapped stream if necessary
        if (secondUnsubscribe == null) {
            // console.log("Subscribing to wrapped")
            secondUnsubscribe = second.subscribeToStream {
                handleInComing()
            }
        }

        sub(invoke())

        return {
            subscriptions.remove(sub)

            // When there are no subscriptions left we unsubscribe from the wrapped streams
            if (subscriptions.isEmpty()) {
                // console.log("Un-Subscribing from wrapped")

                // Unsubscribe from the first stream
                firstUnsubscribe!!()
                firstUnsubscribe = null

                // Unsubscribe from the second stream
                secondUnsubscribe!!()
                secondUnsubscribe = null
            }
        }
    }

    /**
     * Handles new incoming data
     */
    private fun handleInComing() {
        publish(
            invoke()
        )
    }

    /**
     * Publishes the next value
     */
    private fun publish(value: RESULT) {
        subscriptions.forEach { it(value) }
    }
}
