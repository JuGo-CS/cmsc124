class Token(
    val type: TokenType,
    val lexeme: String,
    val literal: Any?,
    val line: Int
) {
    override fun toString(): String {
        val litStr = literal?.toString() ?: "null"
        return "Token(type=$type, lexeme=$lexeme, literal=$litStr, line=$line)"
    }
}