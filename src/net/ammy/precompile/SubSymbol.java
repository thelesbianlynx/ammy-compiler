package net.ammy.precompile;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all the sublynx key symbols
 *
 */
public enum SubSymbol {

    // Program Components.

    GBL, GET, SYM, PKG, ACL, X,
    STC, DEF, FUN,
    TYPE, DATATYPE, ABSDATATYPE, INTERFACE,
    PATTERN, DATA, CONS, DECONS, C,
    ABSDEF, ABSFUN,

    // Expression Components.

    VAL, LOAD, STORE, STOREADDR,
    FIELD, FIELDN, ACCESS, TYPEARG,

    ABS, NEG, NOT, CMPL, COPY,

    ACC, ADD, SUB, MUL, DIV, MOD,
    EQ, NEQ, EQUALS, LESS, LESSEQ, GRT, GRTEQ,
    AND, OR, XOR, BAND, BOR, BXOR, SLEFT, SRIGHT, URIGHT,

    CALL, CONT, STRUCT, V,

    ARRAY, TIMES, MAP,

    COND, SELECT,
    ELSE, DEFAULT, NAME, CASE, RANGE, TYMATCH, MATCH, P,

    WITH,
    LET, VAR, ARG,
    LETV, VARV, ARGV,

    FN, IFN, FOR, RET,

    FOREACH, ITERATE, COUNT, COUNTBY, GUARD, I,

    RAISE, TRY, RESUME,

    DO, END, WHILE, FORLOOP,

    BREAK, CONTINUE, RETURN, RETURNV,

    SUPER, INIT,

    ADDR;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    // Sublynx Symbol Map.
    private final static Map <String, SubSymbol> symbolMap;

    // Get Sublynx Symbol from Current Token.
    public static SubSymbol symbol (String sym) {
        if (sym == null)
            return null;
        SubSymbol symbol = symbolMap.get(sym);
        if (symbol == null)
            throw new RuntimeException(sym + " is not a Sublynx symbol.");
        return symbol;
    }

    // Initialize Symbol Map.
    static {
        symbolMap = new HashMap<>();

        for (SubSymbol symbol : values()) {
            symbolMap.put(symbol.name(), symbol);
        }
    }

}
