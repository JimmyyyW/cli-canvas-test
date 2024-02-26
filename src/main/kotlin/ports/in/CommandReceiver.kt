package ports.`in`

import arrow.core.Either
import domain.AppError
import domain.Command
import domain.mapping.parseCommand

const val INPUT_PROMPT_VALUE = "enter command: "

fun interface CommandReceiver {
    fun receive(): Either<AppError, Command>
}

class CliCommandReceiver(private val s: () -> String) : CommandReceiver {
    override fun receive(): Either<AppError, Command> {
        print(INPUT_PROMPT_VALUE)
        return parseCommand(s.invoke())
    }
}
