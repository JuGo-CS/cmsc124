class Parser(private val tokens: List<Token>) {
    private var current = 0
 
    var hadError = false
        private set
 
    private class ParseError : RuntimeException()
 
    fun parse(): Expr? {
        return try {
            expression()
        } catch (error: ParseError) {
            null
        }
    }


    private fun equality(): Expr {
        //TODO: implement equality
    }

    private fun comparison(): Expr {
        //TODO: implement comparison
    }
    

     private fun term(): Expr {
        var expr = primary()
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            val operator = previous()
            val right = primary()
            expr = Expr.Binary(expr, operator, right)
        }
        return expr
    }

    private fun factor(): Expr {
        //TODO: implement factor
    }

    private fun unary(): Expr {
        //TODO: implement unary
    }

    // helpers

    private fun peek(): Token = tokens[current]
 
    private fun previous(): Token = tokens[current - 1]
 
    private fun isAtEnd(): Boolean = peek().type == TokenType.EOF
 
    private fun advance(): Token {
        if (!isAtEnd()) current++
        return previous()
    }
 
    private fun check(type: TokenType): Boolean {
        if (isAtEnd()) return false
        return peek().type == type
    }
 
    private fun match(vararg types: TokenType): Boolean {
        for (type in types) {
            if (check(type)) {
                advance()
                return true
            }
        }
        return false
    }
 
    private fun consume(type: TokenType, message: String): Token {
        if (check(type)) return advance()
        throw error(peek(), message)
    }
 
    private fun error(token: Token, message: String): ParseError {
        hadError = true
        if (token.type == TokenType.EOF) {
            System.err.println("[line ${token.line}] Error at end: $message")
        } else {
            System.err.println("[line ${token.line}] Error at '${token.lexeme}': $message")
        }
        return ParseError()
    }

    private fun synchronize() {
        //TODO: implement synchronize
    }

}