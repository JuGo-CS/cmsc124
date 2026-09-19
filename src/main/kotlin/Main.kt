import kotlin.system.exitProcess

fun main(args: Array<String>) {
    when {
        args.size == 2 && args[0] == "--tokenize" -> tokenizeFile(args[1])
        args.size == 1 -> runFile(args[0])
        args.isEmpty() -> runPrompt()
        else -> {
            exitProcess(64)
        }
    }
}

fun tokenizeFile(path: String) {
    val source = java.io.File(path).readText()
    val hadError = runScanner(source)
    if (hadError) exitProcess(65)
    exitProcess(0)
}

fun runFile(path: String) {
    println("Hello, maayong buntag!")
    exitProcess(0)
}

fun runPrompt() {
    while (true) {
        print("> ")
        val line = readln()
        runScanner(line)
    }
}

// returns true if scanning failed
fun runScanner(source: String): Boolean {
    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()
    for (token in tokens) {
        println(token)
    }
    return scanner.hadError
}