package domain

sealed interface Command {
    fun execute()
}

data object QuitCommand : Command {
    override fun execute() {
        print("Exiting..")
    }
}