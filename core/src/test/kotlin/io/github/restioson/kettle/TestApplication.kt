package io.github.restioson.kettle

import com.badlogic.gdx.ApplicationAdapter
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

internal class TestApplication : ApplicationAdapter() {

    companion object {

        private class BatchedFunc<R>(val func: (Float) -> R, val coro: Continuation<R>) {
            operator fun invoke(delta: Float) {
                val returnValue = this.func(delta)
                this.coro.resume(returnValue)
            }
        }

        private val batch: Queue<BatchedFunc<*>> = ConcurrentLinkedQueue()

        internal fun <R> schedule(func: (Float) -> R) = async(CommonPool) {
            run(func)
        }

        internal suspend fun <R> run(func: (Float) -> R): R = suspendCoroutine {
            batch.add(BatchedFunc(func, it))
        }

        internal fun execute(delta: Float) {
            while (!batch.isEmpty()) {
                batch.poll().invoke(delta)
            }
        }

    }

    override fun render() {
        super.render()
        execute(0f)
    }
}