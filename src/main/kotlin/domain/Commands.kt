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
        canvas = null
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
            if (!isSuccess) println(line.leftOrNull()?.message)
        }
    }
}

data class RectangleCommand(
    val x1: Int, val y1: Int, val x2: Int, val y2: Int,
) : Command {
    context(ExecutionContext) override fun execute() {
        val rectangle = canvas?.rectangle(x1, y1, x2, y2)
        rectangle?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = rectangle.getOrNull()
            if (!isSuccess) println(rectangle.leftOrNull()?.message)
        }
    }
}

data class FloodFillCommand(
    val x: Int, val y: Int, val char: Char
) : Command {
    context(ExecutionContext) override fun execute() {
        val floodFilled = canvas?.floodFill(y, x, char)
        floodFilled?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = floodFilled.getOrNull()
            if (!isSuccess) println(floodFilled.leftOrNull()?.message)
        }
    }
}