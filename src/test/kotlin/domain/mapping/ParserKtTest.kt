package domain.mapping

import domain.QuitCommand
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.Exception
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ParserKtTest {

    @Nested
    inner class QuitParseTest {
        @ParameterizedTest
        @ValueSource(strings = ["Q", " Q", "Q "])
        fun `should be able to parse valid quit command strings`(input: String) {
            val parseResult = parseCommand(input)

            assertTrue(parseResult.isRight())
            assertEquals(parseResult.getOrNull()!!::class, QuitCommand::class)

        }

    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "q", "Z", "Q1", "C4", "C A 12"])
    fun `should result in InputError when input invalid` (s: String) {
        val parseResult = parseCommand(s)
        Assertions.assertTrue(parseResult.isLeft())
        val left = parseResult.leftOrNull()
        Assertions.assertNotNull(left)
        Assertions.assertEquals(left!!::class, Exception::class)
    }
}
