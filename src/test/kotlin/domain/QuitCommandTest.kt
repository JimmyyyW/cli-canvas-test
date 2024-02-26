package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class QuitCommandTest {

    @Test
    fun `should print exiting on execute`() {
        val out = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        QuitCommand.execute()

        System.setOut(out)

        val result = outputStream.toString(Charsets.UTF_8)

        assertEquals("Exiting..", result)
    }
}