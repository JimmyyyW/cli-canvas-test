package domain

import context.ExecutionContext

sealed interface Command {
    context (ExecutionContext)
    fun execute()
}

data object QuitCommand : Command {
    context (ExecutionContext)
    override fun execute() {
        shouldRepeat = false
        print("Exiting..")
    }
}

data class CreateCommand(val x: Int, val y: Int) : Command {
    context (ExecutionContext)
    override fun execute() {
        canvas = createCanvas(borderValue(x), borderValue(y))
    }

    private val borderValue : (Int) -> Int = { i: Int -> i + 2 }
}

// could potentially merge with Rectangle Command as 'Four Point Command or something'
data class LineCommand(
    val x1: Int, val y1: Int, val x2: Int, val y2: Int,
) : Command {
    context(ExecutionContext) override fun execute() {
        val line = canvas?.line(x1, y1, x2, y2)
        line?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = line.getOrNull()
            if (!isSuccess) println(line.leftOrNull())
        }
    }
}

data class RectangleCommand(
    val x1: Int, val y1: Int, val x2: Int, val y2: Int,
) : Command {
    context(ExecutionContext) override fun execute() {
        val line = canvas?.line(x1, y1, x2, y2)
        line?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = line.getOrNull()
            if (!isSuccess) println(line.leftOrNull())
        }
    }
}