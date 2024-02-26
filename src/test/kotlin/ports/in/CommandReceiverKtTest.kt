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
    fun `should receive command and fail to parse`() {
        val receiver = CliCommandReceiver { "z" }
        assertTrue(receiver.receive().isLeft())
    }
}
