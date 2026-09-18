import kotlin.system.exitProcess

fun main(args: Array<String>) {
    when {
        args.size == 2 && args[0] == "--tokenize" -> runFile(args[1])
        args.isEmpty() -> runPrompt()
        else -> {
            exitProcess(64)
        }
    }
}

fun runFile(path: String) {
    val source = try {
        java.io.File(path).readText()
    } catch (e: java.io.IOException) {
        System.err.println("Could not read file: $path")
        exitProcess(66)
    }

    val hadError = run(source)
    if (hadError) exitProcess(65)

    exitProcess(0)
}

fun runPrompt() {
    print("> ")
    var line = readLine()
    while (line != null) {
        run(line)
        print("> ")
        line = readLine()
    }
    println()
}

// returns true if scanning failed
fun run(source: String): Boolean {
    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()

    if (!scanner.hadError) {
        for (token in tokens) {
            println(token)
        }
    }

    return scanner.hadError
}