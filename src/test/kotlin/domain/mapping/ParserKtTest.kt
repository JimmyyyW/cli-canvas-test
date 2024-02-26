package domain.mapping

import domain.CreateCommand
import domain.InputError
import domain.LineCommand
import domain.QuitCommand
import domain.RectangleCommand
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
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

    @Nested
    inner class CreateParseTest {
        @ParameterizedTest
        @ValueSource(strings = ["C 20 4", "C 12 12"])
        fun `should be able to parse valid quit command strings`(input: String) {
            val parseResult = parseCommand(input)

            assertTrue(parseResult.isRight())
            assertEquals(parseResult.getOrNull()!!::class, CreateCommand::class)

        }
    }

    @Nested
    inner class LineParseTest {
        @ParameterizedTest
        @ValueSource(strings = ["L 1 2 6 2", "L 3 4 5 6"])
        fun `should be able ot parse valid line command strings`(input: String) {
            val parseResult = parseCommand(input)

            assertTrue(parseResult.isRight())
            assertEquals(parseResult.getOrNull()!!::class, LineCommand::class)
        }
    }

    @Nested
    inner class RectangleParseTest {
        @ParameterizedTest
        @ValueSource(strings = ["R 1 2 6 2", "R 3 4 5 6"])
        fun `should be able ot parse valid line command strings`(input: String) {
            val parseResult = parseCommand(input)

            assertTrue(parseResult.isRight())
            assertEquals(parseResult.getOrNull()!!::class, RectangleCommand::class)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "q", "Z", "Q1", "C4", "C A 12", "C 0 0", "L 2 2 2 2 2 2"])
    fun `should result in InputError when input invalid` (s: String) {
        val parseResult = parseCommand(s)
        Assertions.assertTrue(parseResult.isLeft())
        val left = parseResult.leftOrNull()
        Assertions.assertNotNull(left)
        Assertions.assertEquals(left!!::class, InputError::class)
    }
}
