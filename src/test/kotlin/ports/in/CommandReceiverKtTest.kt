package ports.`in`

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CommandReceiverTest {

    @Test
    fun `should receive command and try to parse`() {
        val receiver = CliCommandReceiver { "Q" }
        assertTrue(receiver.receive().isRight())
    }

    @Test
    fun `should receive create command and try to parse`() {
        val receiver = CliCommandReceiver { "C 20 4" }
        assertTrue(receiver.receive().isRight())
    }

    @Test
    fun `should receive line command and try to parse`() {
        val receiver = CliCommandReceiver { "L 1 2 6 2" }
        assertTrue(receiver.receive().isRight())
    }

    @Test
    fun `should receive command and fail to parse`() {
        val receiver = CliCommandReceiver { "z" }
        assertTrue(receiver.receive().isLeft())
    }
}
