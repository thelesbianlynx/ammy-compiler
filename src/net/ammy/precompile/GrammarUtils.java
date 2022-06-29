package net.ammy.precompile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Defines utility methods to help compute sets of interest for a grammar (first, follow, etc.).
 *
 */
public class GrammarUtils {

    /**
     * Structure containing the sets of interest of a grammar.
     * Contains the nullable set, the first sets, and the follow sets.
     */
    public static class GrammarInfo {
        public final Grammar grammar;

        public final Set <Symbol> nullable;
        public final Map <Symbol, Set <Symbol>> first;
        public final Map <Symbol, Set <Symbol>> follow;

        //<editor-fold defaultstate="collapsed" desc="Constructor">
        public GrammarInfo (
                Grammar _grammar,
                Set<Symbol> _nullable,
                Map<Symbol, Set<Symbol>> _first,
                Map<Symbol, Set<Symbol>> _follow) {
            grammar = _grammar; nullable = _nullable;
            first = _first; follow = _follow;
        }
        //</editor-fold>
    }

    /**
     * Compute the nullable, first, and follow sets of a grammar.
     * @param grammar The grammar for which the sets will be computed.
     * @return The GrammarInfo structure containing the computed sets.
     */
    public static GrammarInfo getGrammarInfo (Grammar grammar) {
        // Symbols.
        List <Symbol> symbols = grammar.symbols();
        // Productions.
        List <Production> productions = grammar.productionRules();
        // Productions-Map.
        Map <Symbol, List <Production>> productionsMap = grammar.productionMap();

        // Nullable.
        HashSet <Symbol> nullable = new HashSet<>();

        // Compute Nullables.
        boolean cont;
        do {
            cont = false;
            for (Production prod : productions) {
                Symbol LS = prod.variable();
                List <Symbol> RS = prod.production();

                if (nullable.contains(LS)) continue;

                if (prod.production().isEmpty()) {
                    nullable.add(LS);
                    cont = true;
                } else {
                    N:{
                        for (Symbol s : RS) {
                            if (!(s.isVariable() && nullable.contains(s))){
                                break N;
                            }
                        }
                        nullable.add(LS);
                        cont = true;
                    }
                }
            }
        } while (cont);

        // First.
        HashMap <Symbol, Set <Symbol>> first = new HashMap<>();
        // Follow.
        HashMap <Symbol, Set <Symbol>> follow = new HashMap<>();

        // Initialize.
        for (Symbol s : symbols) {
            HashSet <Symbol> newfirst = new HashSet<>();
            if (!s.isVariable()){
                newfirst.add(s);
            }
            first.put(s, newfirst);
            follow.put(s, new HashSet<>());
        }

        // Compute Firsts and Follows.
        do {
            cont = false;
            for (Production prod : productions) {
                Symbol LS = prod.variable();
                List <Symbol> RS = prod.production();

                int k = RS.size();

                boolean addable = true;
                for (int i = 0; i < k; ++i) {
                    // Current Symbol.
                    Symbol CS = RS.get(i);

                    // First.
                    if (addable) {
                        if ( first.get(LS).addAll(first.get(CS)) ) cont = true;
                        addable = nullable.contains(CS);
                    }

                    // Follow.
                    if (CS.isVariable()) {
                        // Type 1.
                        boolean a = true;
                        for (int j = i + 1; j < k; ++j) {
                            if (a) {
                                if ( follow.get(CS).addAll(first.get(RS.get(j))) ) cont = true;
                                a = nullable.contains(RS.get(j));
                            } else break;
                        }
                        // Type 2.
                        F:{
                            for (int j = i + 1; j < k; ++j)
                                if (!nullable.contains(RS.get(j))) break F;
                            if ( follow.get(CS).addAll(follow.get(LS)) ) cont = true;
                        }
                    }
                }
            }
        } while (cont);

        return new GrammarInfo(grammar, nullable, first, follow);
    }

}
