package domain.mapping

import domain.CreateCommand
import domain.FloodFillCommand
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

    @Nested
    inner class FloodFillParseTest {
        @ParameterizedTest
        @ValueSource(strings = ["B 10 3 o", "B 9 29 1"])
        fun `should be able ot parse valid line command strings`(input: String) {
            val parseResult = parseCommand(input)

            assertTrue(parseResult.isRight())
            assertEquals(parseResult.getOrNull()!!::class, FloodFillCommand::class)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [
        " ", "q", "Z", "Q1",
        "C4", "C A 12", "C 0 0",
        "L 2 2 2 2 2 2", "L 6 3 6 4 X",
        "R 16 1 1 1 1", "R32 132 11",
        "B 10 3 foo", "B xy 10 2"
    ])
    fun `should result in InputError when input invalid` (s: String) {
        val parseResult = parseCommand(s)
        Assertions.assertTrue(parseResult.isLeft())
        val left = parseResult.leftOrNull()
        Assertions.assertNotNull(left)
        Assertions.assertEquals(left!!::class, InputError::class)
    }
}
