import kotlin.system.exitProcess

fun main(args: Array<String>) {
    when {
        args.size == 2 && args[0] == "--tokenize" -> runFile(args[1])
        args.isEmpty() -> runPrompt()
        else -> {
            System.err.println("Usage: ./run --tokenize <file>  OR  ./run")
            exitProcess(64)
        }
    }
}

fun runFile(path: String) {
    val source = java.io.File(path).readText()
    val hadError = run(source)
    if (hadError) exitProcess(65)
    exitProcess(0)
}

fun runPrompt() {
    while (true) {
        print("> ")
        val line = readLine() ?: break
        run(line)
        // REPL never dies on a bad line
    }
}

// returns true if scanning failed
fun run(source: String): Boolean {
    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()
    for (token in tokens) {
        println(token)
    }
    return scanner.hadError
}