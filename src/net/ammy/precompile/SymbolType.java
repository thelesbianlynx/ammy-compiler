package net.ammy.precompile;

/**
 * The Type of a lexical symbol.
 * Terminal or Non-terminal (Variable).
 * Terminal types also determines the lexical type of the symbol.
 *
 */
public enum SymbolType {
    // Basic Types.
    Terminal, Variable,

    // Lexial Types.
    ID, KEYWORD, OPERATOR, EOF,
    INT, BYTE_INT, SHORT_INT, LONG_INT, REAL, DOUBLE_REAL, STRING, CHARACTER;

    // Get Symbol Type From String.
    public static SymbolType getSymbolType (String type) {
        switch (type) {
            case "sym":
                return Terminal;
            case "var":
                return Variable;
            case "id":
                return ID;
            case "int":
                return INT;
            case "byte":
                return BYTE_INT;
            case "short":
                return SHORT_INT;
            case "long":
                return LONG_INT;
            case "float":
                return REAL;
            case "double":
                return DOUBLE_REAL;
            case "str":
                return STRING;
            case "chr":
                return CHARACTER;
            case "key":
                return KEYWORD;
            case "op":
                return OPERATOR;
            case "eof":
                return EOF;
        }
        return Terminal;
    }
}
