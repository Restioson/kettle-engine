package io.github.restioson.kettle.script

import io.github.restioson.kettle.api.Threadsafe
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.Queue
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
 *     // Suspends coroutine until run
 *     // This will run in the update loop, which makes sure that
 *     // every hardware running the game will have the same speed
 *     // of updating
 *     val returnValue = Batch.run { delta -> // delta time
 *          return updateGamestate(delta)
 *     }
 *
 *     // Returns a Deffered<T> which deffers the return
 *     // value of the function.
 *     // Use this if you need to schedule a func and
 *     // continue with coroutine, maybe checking back
 *     // to the Deffered<T> returned
 *     val defferedReturn = Batch.schedule {
 *         updateGamestateNoReturn(it) // it is the default
 *                                     // param name
 *     }
 *
 * }
 *
 * // Schedule can be called outside of coroutines!
 * // Run can only be called in coroutines and suspending
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
     * Schedules a function to be run
     *
     * This method is threadsafe
     *
     * @param func the function to be scheduled
     * @return an instance of `Deffered<R>` which can be used to get the return of `func`
     */
    @Threadsafe
    fun <R> schedule(func: (Float) -> R) = async(CommonPool) {
        Batch.run(func)
    }

    /**
     * Schedules a function to be run, suspends the coroutine, and resumes it with the return of the function.
     *
     * This method is threadsafe
     *
     * Unsuspends the coroutine with the return of `func` (via [BatchedFunc])
     *
     * @param func the function to be scheduled
     *
     */
    @Threadsafe
    suspend fun <R> run(func: (Float) -> R): R = suspendCoroutine {
        batch.add(BatchedFunc(func, it))
    }

    /**
     * Runs the batched functions
     *
     * This method is threadsafe
     *
     * @param delta the time from this tick to last
     */
    @Threadsafe
    internal fun execute(delta: Float) {
        while (!batch.isEmpty()) {
            batch.poll().invoke(delta)
        }
    }

}
