package domain

import arrow.core.Either

const val DEFAULT_CHAR = ' '
const val TOP_BOTTOM = '-'
const val SIDE = '|'

typealias Canvas = Array<out CharArray>

fun createCanvas(x: Int, y: Int): Canvas {
    return Canvas(y) { CharArray(x) }.onEachIndexed { index, chars ->
        if (index == 0 || index == y - 1) {
            chars.forEachIndexed { idx, _ -> chars[idx] = TOP_BOTTOM }
        } else {
            chars.forEachIndexed { idx, _ -> chars[idx] = DEFAULT_CHAR }
            chars[0] = SIDE
            chars[x - 1] = SIDE
        }
    }
}

fun Canvas.line(x1: Int, y1: Int, x2: Int, y2: Int): Either<AppError, Canvas> {
    return when {
        !isInWriteSpace(x1, y1) || !isInWriteSpace(x2, y2)
            -> Either.Left(CanvasDrawingError("coordinate out of bounds"))

        !isHorizontal(x1, x2) && !isVertical(y1, y2)
            -> Either.Left(CanvasDrawingError("only vertical/horizontal lines supported"))

        else -> {
            (x1..x2).forEach { x ->
                (y1..y2).forEach { y ->
                    this[y][x] = 'x'
                }
            }
            Either.Right(this)
        }
    }
}

fun Canvas.rectangle(x1: Int, y1: Int, x2: Int, y2: Int): Either<AppError, Canvas> {
    return when {
        !isInWriteSpace(x1, y1) || !isInWriteSpace(x2, y2)
            -> Either.Left(CanvasDrawingError("coordinate out of bounds"))

        else -> {
            (x1..x2).forEach { x ->
                (y1..y2).forEach { y ->
                    if (x == x1 || y == y1 || x == x2 || y == y2) {
                        this[y][x] = 'x'
                    }
                }
            }
            Either.Right(this)
        }
    }
}

fun Canvas.floodFill(x: Int, y: Int, char: Char): Either<AppError, Canvas> {
    if (isOutOfBounds(this, x, y)) {
        return Either.Left(CanvasDrawingError("flood fill out of bounds"))
    }
    val original = this[x][y]
    if (char == original) {
        return Either.Left(CanvasDrawingError("cannot flood fill on matching character."))
    }
    floodFill(x, y, original, char)
    return Either.Right(this)
}

private fun isOutOfBounds(canvas: Canvas, x: Int, y: Int): Boolean {
    return x == 0 || y == 0 || x > canvas.size -2 || y > canvas[0].size -2
}

private fun Canvas.floodFill(x: Int, y: Int, original: Char, char: Char) {
    if (x == 0 || y == 0 || x == this.size || y == this[0].size) {
        return
    }
    if (this[x][y] == original) {
        this[x][y] = char
        floodFill(x + 1, y, original, char)
        floodFill(x, y + 1, original, char)
        floodFill(x - 1,  y, original, char)
        floodFill(x, y - 1, original, char)
    }
}

private fun Canvas.isInWriteSpace(x: Int, y: Int): Boolean {
    return when {
        x == 0 || y == 0 -> false
        y >= this.size -> false
        any { x >= it.size } -> false
        else -> true
    }
}

private fun isHorizontal(y1: Int, y2: Int): Boolean = y1 == y2
private fun isVertical(x1: Int, x2: Int): Boolean = x1 == x2
