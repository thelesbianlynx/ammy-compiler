package net.ammy.precompile;

import java.util.List;

/**
 * Represents a derivation rule in a grammar.
 * 
 */
public class Production {

    /* Fields */

    //Index.
    private final int index;

    //Derivation symbols.
    private final Symbol variable;
    private final List <Symbol> production;

    /* Constructor */

    public Production (int _index, Symbol _var, List <Symbol> _prod) {
        index = _index;
        variable = _var;
        production =  _prod;
    }

    /* Operations */

    public int index () {
        return index;
    }

    public Symbol variable () {
        return variable;
    }

    public List <Symbol> production () {
        return production;
    }

    public Grammar grammar () {
        return variable.grammar();
    }

    /* Overrides */

    @Override
    public String toString () {
        StringBuilder r = new StringBuilder();
        r.append(variable);
        r.append(" ::");
        for (Symbol symbol : production)
            r.append(" ").append(symbol);
        return r.toString();
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Production) {
            Production p = (Production) obj;
            return index == p.index;
        }
        return false;
    }

}
