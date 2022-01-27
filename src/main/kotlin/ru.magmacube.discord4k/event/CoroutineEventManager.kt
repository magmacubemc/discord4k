package ru.magmacube.discord4k.event

import kotlinx.coroutines.*
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.IEventManager
import ru.magmacube.discord4k.Log
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Duration

private val log by Log<CoroutineEventManager>()
class CoroutineEventManager(
    scope: CoroutineScope = GlobalScope,
    /** Timeout [Duration] each event listener is allowed to run. Set to [Duration.INFINITE] for no timeout. Default: [Duration.INFINITE] */
    var timeout: Duration = Duration.INFINITE
) : IEventManager, CoroutineScope by scope {
    private val listeners = CopyOnWriteArrayList<Any>()

    private fun timeout(listener: Any) = when {
        listener is CoroutineEventListener && listener.timeout != EventTimeout.Inherit -> listener.timeout.time
        else -> timeout
    }

    override fun handle(event: GenericEvent) {
        launch {
            for (listener in listeners) {
                val actualTimeout = timeout(listener)
                if (actualTimeout.isPositive() && actualTimeout.isFinite()) {
                    // Timeout only works when the continuations implement a cancellation handler
                    val result = withTimeoutOrNull(actualTimeout.inWholeMilliseconds) {
                        runListener(listener, event)
                    }
                    if (result == null) {
                        log.debug("Event of type ${event.javaClass.simpleName} timed out.")
                    }
                } else {
                    runListener(listener, event)
                }
            }
        }
    }

    private suspend fun runListener(listener: Any, event: GenericEvent) {
        when (listener) {
            is CoroutineEventListener -> listener.onEvent(event)
            is EventListener -> listener.onEvent(event)
        }
    }

    override fun register(listener: Any) {
        listeners.add(when (listener) {
            is EventListener, is CoroutineEventListener -> listener
            else -> throw IllegalArgumentException("Listener must implement either EventListener or CoroutineEventListener")
        })
    }

    override fun getRegisteredListeners(): MutableList<Any> = mutableListOf(listeners)

    override fun unregister(listener: Any) {
        listeners.remove(listener)
    }

    /**
     * Opens an event listener scope for simple hooking.
     *
     * ## Example
     *
     * ```kotlin
     * manager.listener<MessageReceivedEvent> { event ->
     *     println(event.message.contentRaw)
     * }
     * ```
     *
     * @param[timeout] The timeout [Duration] to use for this listener, or null to use the default from the event manager
     * @param[consumer] The event consumer function
     *
     * @return[CoroutineEventListener] The created event listener instance (can be used to remove later)
     */
    inline fun <reified T : GenericEvent> listener(timeout: Duration? = null, crossinline consumer: suspend CoroutineEventListener.(T) -> Unit): CoroutineEventListener {
        return object : CoroutineEventListener {
            override val timeout: EventTimeout
                get() = timeout.toTimeout()

            override fun cancel() {
                return unregister(this)
            }

            override suspend fun onEvent(event: GenericEvent) {
                if (event is T)
                    consumer(event)
            }
        }.also { register(it) }
    }
}