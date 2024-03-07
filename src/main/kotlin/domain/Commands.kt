package domain

import arrow.core.Either
import context.ExecutionContext

sealed interface Command {
    context (ExecutionContext)
    fun execute()
}

interface Sanitized {
    fun validate(): Either<AppError, Command>
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
) : Command, Sanitized {
    context(ExecutionContext) override fun execute() {
        val line = canvas?.line(x1, y1, x2, y2)
        line?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = line.getOrNull()
            if (!isSuccess) println(line.leftOrNull()?.message)
        }
    }

    override fun validate(): Either<AppError, Command> {
        return if (x1 > 0 && y1 > 0 && x2 >0 && y2 > 0) {
            Either.Right(this)
        } else {
            Either.Left(InputError("negatives not allowed"))
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
) : Command, Sanitized {
    context(ExecutionContext) override fun execute() {
        val floodFilled = canvas?.floodFill(y, x, char)
        floodFilled?.isRight()?.let { isSuccess ->
            if (isSuccess) canvas = floodFilled.getOrNull()
            if (!isSuccess) println(floodFilled.leftOrNull()?.message)
        }
    }

    override fun validate(): Either<AppError, Command> {
        return if (x > 0 && y > 0) {
            Either.Right(this)
        } else {
            Either.Left(InputError("negatives not allowed"))
        }
    }
}

