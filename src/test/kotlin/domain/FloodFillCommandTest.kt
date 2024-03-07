package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FloodFillCommandTest {


    @Test
    fun `should ensure all integer command parameters are positive`() {
        val command = FloodFillCommand(-1, 4, 'a')

        val result = command.validate()

        assertTrue(result.isLeft())
    }

    @Test
    fun `should return the command when inputs are valid`() {
        val command = FloodFillCommand(1, 4, 'a')

        val result = command.validate()

        assertTrue(result.isRight())
    }

}
