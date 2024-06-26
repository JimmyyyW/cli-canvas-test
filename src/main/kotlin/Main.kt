import context.ExecutionContext
import domain.Canvas
import ports.`in`.CliCommandReceiver
import ports.`in`.CommandReceiver
import ports.out.render

data class AppConfig(
    val receiver: CommandReceiver,
    val render: (Canvas) -> Unit
)

val appConfig = AppConfig(
    receiver = CliCommandReceiver(::readln),
    render = ::render
)

fun main() {
    with(appConfig) {
       with(ExecutionContext()) {
           repeat {
               receiver.receive()
                   .onLeft { println(it.message) }
                   .onRight { execute(it); canvas?.let(render) }
           }
       }
    }
}
