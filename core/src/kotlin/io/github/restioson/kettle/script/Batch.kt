package io.github.restioson.kettle.script

import io.github.restioson.kettle.api.Threadsafe
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Object for batching functions from coroutines to be run inside the update loop
 *
 * Example:
 *
 * ```kotlin
 * launch(CommonPool) {
 *     waitForSomething()
 *
 *     // Suspends coroutine until run
 *     // This will run in the update loop, which makes sure that
 *     // every hardware running the game will have the same speed
 *     // of updating
 *     val returnValue = Batch.run { delta -> // delta time
 *          return updateGamestate(delta)
 *     }
 *
 *     // Cannot get return value from schedule, use run for that
 *     // Use this if you need to asynchronously schedule a func
 *     // to be run on the main thread in the update loop
 *     Batch.schedule {
 *         updateGamestateNoReturn(it) // it is the default
 *                                     // param name
 *     }
 *
 * }
 *
 * // Schedule can be called outside of coroutines!
 * // run can only be called in coroutines and suspending
 * // functions, as it is a suspending function
 * Batch.schedule {
 *     updateGamestate()
 * }
 */
object Batch {

    /**
     * Represents a function to be run internally
     */
    private class BatchedFunc<R>(val func: (Float) -> R, val coro: Continuation<R>?) {

        /**
         * Run the function and optionally resume the coroutine it is from with value
         */
        operator fun invoke(delta: Float) {
            val returnValue = this.func(delta)
            this.coro?.resume(returnValue)
        }
    }

    /**
     * The internal threadsafe queue used to store batched functions
     */
    private val batch: Queue<BatchedFunc<*>> = ConcurrentLinkedQueue()

    /**
     * Schedules a function to be run.
     *
     * This method is threadsafe
     */
    @Threadsafe
    fun <R> schedule(func: (Float) -> R) = batch.add(BatchedFunc(func, null))

    /**
     * Schedules a function to be run, suspends the coroutine, and resumes it with the return of the function.
     *
     * This method is threadsafe
     */
    @Threadsafe
    suspend fun <R> run(func: (Float) -> R): R = suspendCoroutine {
        batch.add(BatchedFunc(func, it))
    }

    /**
     * Runs the batched functions
     *
     * This method is threadsafe
     */
    @Threadsafe
    internal fun execute(delta: Float) {
        while (!batch.isEmpty()) {
            batch.poll().invoke(delta)
        }
    }

}
