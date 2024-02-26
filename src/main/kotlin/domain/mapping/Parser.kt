package domain.mapping

import arrow.core.Either
import domain.Command
import domain.QuitCommand

fun parseCommand(input: String): Either<Exception, Command> =
    input.trim()
        .split(" ")
        .firstOrNull()
        ?.let { charCommandMapping[it]
            ?.let { cmd -> Either.Right(cmd) }
            ?: Either.Left(Exception(""))
        }
        ?: Either.Left(Exception("Unable to parse command: $input"))


private val charCommandMapping: Map<String, Command> = mapOf(
    "Q" to QuitCommand
)
