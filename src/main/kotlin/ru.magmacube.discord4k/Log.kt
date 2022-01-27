package ru.magmacube.discord4k

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KProperty

object Log {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): Logger {
        return LoggerFactory.getLogger(thisRef!!::class.java)!!
    }

    /**
     * Shortcut for [LoggerFactory.getLogger] with string name
     *
     * @param name
     *        The name of the logger
     */
    operator fun invoke(name: String) = lazy {
        LoggerFactory.getLogger(name)!!
    }

    // for some reason you can't link specific overloads...
    /**
     * Shortcut for [LoggerFactory.getLogger] with class parameter
     *
     * @param[T]
     *       The type the logger is for
     */
    inline operator fun <reified T> invoke() = lazy {
        LoggerFactory.getLogger(T::class.java)!!
    }
}