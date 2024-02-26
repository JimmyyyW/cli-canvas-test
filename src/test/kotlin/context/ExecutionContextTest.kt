package context

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExecutionContextTest {

    @Test
    fun `should stop when repeat is false`() {
        val ctx = ExecutionContext()
        var counter = 0
        ctx.repeat {
            if (counter == 2) {
                ctx.shouldRepeat = false
            }
           counter++
        }
        assertTrue(counter == 3)
    }

}