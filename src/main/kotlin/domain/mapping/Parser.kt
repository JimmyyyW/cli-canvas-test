package domain.mapping

import arrow.core.Either
import domain.AppError
import domain.Command
import domain.CreateCommand
import domain.InputError
import domain.LineCommand
import domain.QuitCommand
import domain.RectangleCommand
import java.lang.NumberFormatException
import kotlin.reflect.KClass

fun parseCommand(input: String): Either<AppError, Command> = input.trim()
        .split(" ")
        .toCommand()

private fun List<String>.toCommand(): Either<AppError, Command> {
    return when (this.firstOrNull()) {
        "Q" -> Either.Right(QuitCommand)
        "C" -> this.toCreateCommand()
        "L" -> this.toFourPointCommand(LineCommand::class)
        "R" -> this.toFourPointCommand(RectangleCommand::class)
        else -> Either.Left(InputError())
    }
}

private fun List<String>.toCreateCommand(): Either<AppError, CreateCommand> {
    if (this.size != 3) {
        return Either.Left(InputError("expected 2 values, an X and Y value"))
    }
    return try {
        val x = this[1].toInt()
        val y = this[2].toInt()
        if (x == 0 || y == 0) Either.Left(InputError("Canvas bounds must be greater than 0"))
        else Either.Right(CreateCommand(x, y))
    } catch (e: NumberFormatException) {
        Either.Left(InputError("Failed to parse ${this[1]} or ${this[2]} to integer"))
    }
}

private fun List<String>.toFourPointCommand(type: KClass<out Command>): Either<AppError, Command> {
    if (this.size != 5) {
        return Either.Left(InputError("expected 2 values, an X and Y value"))
    }
    return try {
        val x1 = this[1].toInt()
        val y1 = this[2].toInt()
        val x2 = this[3].toInt()
        val y2 = this[4].toInt()
        when (type) {
            LineCommand::class -> Either.Right(LineCommand(x1, y1, x2, y2))
            RectangleCommand::class -> Either.Right(RectangleCommand(x1, y1, x2, y2))
            else -> Either.Left(InputError("asda"))
        }
    } catch (e: NumberFormatException) {
        Either.Left(InputError("Failed to parse integer"))
    }
}
