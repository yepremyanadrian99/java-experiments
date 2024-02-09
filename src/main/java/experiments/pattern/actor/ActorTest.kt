package experiments.pattern.actor

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

interface Command
class GetResult(val result: CompletableDeferred<Int>) : Command
class Increment : Command

data class Counter(var counter: Int = 0) {

    fun get(): Int = counter

    fun inc() {
        ++counter
    }
}

fun CoroutineScope.counterActor(counter: Counter = Counter()) = actor<Command> {
    channel.consumeEach {
        when (it) {
            is GetResult -> it.result.complete(counter.get())
            is Increment -> counter.inc()
        }
    }
}

fun main() = runBlocking(Dispatchers.Default) {
    val counterActor = counterActor()
    massiveRun {
        counterActor.send(Increment())
    }
    val resultDeferred = CompletableDeferred<Int>()
    counterActor.send(GetResult(resultDeferred))
    val result = resultDeferred.await()
    println("Counter: $result")
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val coroutines = 100
    val operations = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(coroutines) {
                launch {
                    repeat(operations) { action() }
                }
            }
        }
    }
    println("Completed ${coroutines * operations} actions in $time ms")
}
