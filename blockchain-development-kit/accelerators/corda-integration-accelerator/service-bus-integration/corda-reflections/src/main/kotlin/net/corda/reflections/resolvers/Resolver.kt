package net.corda.reflections.resolvers

import kotlin.reflect.KClass


/**
 * Describes a value resolver that takes an input value and
 * if possible converts to an output value of type T.
 */
interface Resolver<T> {

    /**
     * Can this resolve to the specified output type?
     *
     * @param outputType
     * @return true if matches
     */
    fun matchesOutputType(outputType: Class<*>): Boolean {
        val result =  outputType == type().java
        return result
    }

    /**
     * Try and resolve the input value to the specified output type. This is NOT
     * called if the inputValue is null.
     *
     * Resolvers can potentially be chained (will possibly need a way of specifying
     * order of firing in the future). If the value can not be resolved this method should
     * return null and avoid raising exceptions. If no resolver succeeds an exception will
     * be raised at the end for an unmatched param.
     *
     * @param inputValue
     * @return
     */
    fun resolveValue(inputValue: Any): T?

    /**
     * Provide meta data information that would
     * otherwise be extracted
     */
    fun type(): KClass<*>
}