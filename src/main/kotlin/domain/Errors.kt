package domain

sealed interface AppError {
    val message: String
}

data class InputError(override val message: String = INPUT_HELP_VALUE) : AppError

data class CanvasDrawingError(override val message: String) : AppError

const val INPUT_HELP_VALUE = """
    Please enter a valid command:
    Q - quit
    C x(int) y(int) - create canvas
    L x1(int) y1(int) x2(int) y2(int) - draw line
    R x1(int) y1(int) x2(int) y2(int) - draw a rectangle
    B x(int) y(int) c(char) - flood fill with <char>
"""
