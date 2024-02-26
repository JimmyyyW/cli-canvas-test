package ports.`in`

import arrow.core.Either
import domain.Command
import domain.mapping.parseCommand

const val INPUT_PROMPT_VALUE = "enter command: "

//fun receiveCliCommand(): Either<Exception, Command> = print(INPUT_PROMPT_VALUE)
//    .run { parseCommand(readln()) }

fun interface CommandReceiver {
    fun receive(): Either<Exception, Command>
}

class CliCommandReceiver(private val s: () -> String) : CommandReceiver {
    override fun receive(): Either<Exception, Command> {
        print(INPUT_PROMPT_VALUE)
        return parseCommand(s.invoke())
    }
}
