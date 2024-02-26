package context

import domain.Canvas
import domain.Command

data class ExecutionContext(
    var canvas: Canvas? = null
) {

    var shouldRepeat = true

    fun execute(command: Command) {
        command.execute()
    }

    fun repeat(block: () -> Unit) {
        do {
            block.invoke()
        } while (shouldRepeat)
    }
}
