package ports.out

import domain.Canvas

fun render(canvas: Canvas): Unit = canvas.draw()

private fun Canvas.draw() = this.forEach { chars ->
    chars.forEach { char ->
        print("$char")
    }
    print("\n")
}
