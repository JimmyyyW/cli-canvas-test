package ports.out

import domain.Canvas
import domain.createCanvas
import domain.floodFill
import domain.line
import domain.rectangle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class RendererTest {
    @Test
    fun `should draw a 4x4 canvas`() {
        val oldOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val canvas = Canvas(4) { charArrayOf('X', 'X', 'X', 'X') }
        render(canvas)

        System.setOut(oldOut)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals(expectedDrawResult(), result)

    }

    @Test
    fun `should draw a 20x4 canvas`() {
        val oldOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val canvas = createCanvas(22, 6)
        render(canvas)

        System.setOut(oldOut)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals(expectedDrawResult20x4(), result)

    }

    @Test
    fun `should draw a 20x4 canvas with L 1 2 6 2`() {
        val oldOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val canvas = createCanvas(22, 6)
        canvas.line(1, 2, 6, 2)
        render(canvas)

        System.setOut(oldOut)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals(expectedDrawResult20x4withLine(), result)

    }

    @Test
    fun `should draw a 20x4 canvas with R 16 1 20 3`() {
        val oldOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val canvas = createCanvas(22, 6)
        canvas.rectangle(16, 1, 20, 3)
        render(canvas)

        System.setOut(oldOut)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals(expectedDrawResult20x4withRectangle(), result)

    }

    @Test
    fun `should draw a 20x4 canvas flood fill B 10 3 o`() {
        val oldOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val canvas = createCanvas(22, 6)
        canvas.rectangle(16, 1, 20, 3)
        canvas.floodFill(3, 10, 'o')
        render(canvas)

        System.setOut(oldOut)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals(expectedDrawResult20x4withFloodFill(), result)

    }

    private fun expectedDrawResult() = """
        XXXX
        XXXX
        XXXX
        XXXX
       
        """.trimIndent()

    private fun expectedDrawResult20x4() = """
       ${"-".repeat(22)}
       |${" ".repeat(20)}|
       |${" ".repeat(20)}|
       |${" ".repeat(20)}|
       |${" ".repeat(20)}|
       ${"-".repeat(22)}
       
    """.trimIndent()

    private fun expectedDrawResult20x4withLine() = """
       ${"-".repeat(22)}
       |${" ".repeat(20)}|
       |${"x".repeat(6)}${" ".repeat(14)}|
       |${" ".repeat(20)}|
       |${" ".repeat(20)}|
       ${"-".repeat(22)}
       
    """.trimIndent()

    private fun expectedDrawResult20x4withRectangle() = """
       ${"-".repeat(22)}
       |${" ".repeat(15)}xxxxx|
       |${" ".repeat(15)}x   x|
       |${" ".repeat(15)}xxxxx|
       |${" ".repeat(20)}|
       ${"-".repeat(22)}
       
    """.trimIndent()

    private fun expectedDrawResult20x4withFloodFill() = """
       ${"-".repeat(22)}
       |${"o".repeat(15)}xxxxx|
       |${"o".repeat(15)}x   x|
       |${"o".repeat(15)}xxxxx|
       |${"o".repeat(20)}|
       ${"-".repeat(22)}
       
    """.trimIndent()
}