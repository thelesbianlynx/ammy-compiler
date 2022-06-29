package net.ammy.precompile;

/**
 * Represents a symbol in the grammar. A symbol can either be terminal, meaning it represents a literal
 * token, or variable, meaning it represents a derivation.
 *
 */
public class Symbol {

    /* Fields */

    //Index of this symbol.
    private final int index;

    //Symbol Type
    private final SymbolType type;

    //Textual name of this symbol.
    private final String text;

    //Grammar associated with this symbol.
    private final Grammar grammar;

    /* Constructor */

    public Symbol (int _index, SymbolType _type, String _text, Grammar _grammar) {
        index = _index;
        type = _type;
        text = _text;
        grammar = _grammar;
    }

    /* Operations */

    public int index () {
        return index;
    }

    public SymbolType type () {
        return type;
    }

    public boolean isVariable () {
        return type == SymbolType.Variable;
    }

    public String text () {
        return text;
    }

    public Grammar grammar () {
        return grammar;
    }

    /* Overrides */

    @Override
    public String toString () {
        return text;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Symbol) {
            Symbol s = (Symbol) obj;
            return index == s.index;
        }
        return false;
    }


}
