package net.ammy.precompile;

import java.util.List;

/**
 * Represents a Language Token. A Token is either terminal symbol read from a source file, or it
 * is a variable symbol parsed from a stream of tokens over a grammar. In the latter case, The token
 * representing the start symbol acts as the root of the abstract syntax tree.
 * 
 */
public class Token {

    /* Fields */

    //Token Symbol.
    private final Symbol symbol;

    //Token Lexeme.
    private final String lexeme;

    //Location of Token.
    private final Location location;

    //Grammar Rule and Sub-Tokens.
    // (both null if terminal symbol)
    private final Production productionRule;
    private final List <Token> derivation;


    /* Constructors */

    //Create Token.
    public Token (Symbol _symbol) {
        symbol = _symbol;
        lexeme = null;
        location = null;
        productionRule = null;
        derivation = null;
    }

    //Create Token.
    public Token (Symbol _symbol, String _lexeme) {
        symbol = _symbol;
        lexeme = _lexeme;
        location = null;
        productionRule = null;
        derivation = null;
    }

    //Create Token.
    public Token (Symbol _symbol, Location _loc) {
        symbol = _symbol;
        lexeme = null;
        location = _loc;
        productionRule = null;
        derivation = null;
    }

    //Create Token.
    public Token (Symbol _symbol, String _lexeme, Location _loc) {
        symbol = _symbol;
        lexeme = _lexeme;
        location = _loc;
        productionRule = null;
        derivation = null;
    }

    //Create Derivation.
    public Token (Symbol _symbol, Production _prod, List <Token> _derivation) {
        symbol = _symbol;
        lexeme = null;
        location = null;
        productionRule = _prod;
        derivation = _derivation;
    }

    /* Operations */

    public Symbol symbol () {
        return symbol;
    }

    public boolean isDerivation () {
        return symbol.isVariable();
    }

    public String lexeme () {
        return lexeme;
    }

    public Location location () {
        return location;
    }

    public Production productionRule () {
        return productionRule;
    }

    public List <Token> derivation () {
        return derivation;
    }

    /* Overrides */

    @Override
    public String toString () {
        return tokenWriter(new StringBuilder(), 0).toString();
    }

    private StringBuilder tokenWriter (StringBuilder s, int lvl) {

        // Append Level of token.
        for (int i = 0; i < lvl; ++i)
            s.append("-");


        if (isDerivation()) {
            s.append(">").append(productionRule.toString());
            for (Token sub : derivation){
                s.append("\n");
                sub.tokenWriter(s, lvl+1);
            }
        } else {
            s.append(symbol.text());
            if (lexeme != null)
                s.append(" (").append(lexeme.toString()).append(")");
        }

        return s;
    }


}
