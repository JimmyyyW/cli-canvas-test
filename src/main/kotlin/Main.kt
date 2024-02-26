import ports.`in`.CliCommandReceiver
import ports.`in`.CommandReceiver

data class AppConfig(
    //todo: if time see if this is testable
//    val receiveCommand: () -> Either<Exception, Command>
    val receiver: CommandReceiver
)

val appConfig = AppConfig(
//    receiveCommand = ::receiveCliCommand
    receiver = CliCommandReceiver(::readln)
)

fun main() {
    with(appConfig) {
        receiver.receive()
            .onLeft { println(it.message) }
            .onRight { it.execute() }
    }
}
