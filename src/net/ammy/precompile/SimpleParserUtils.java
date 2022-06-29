package net.ammy.precompile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.ammy.precompile.ParserUtils.ParseMachine;
import net.ammy.precompile.ParserUtils.ReduceAction;
import net.ammy.precompile.ParserUtils.ShiftAction;

/**
 *
 */
public class SimpleParserUtils {
    /* Classes */

    public static class LRItem {
        public final int dot;
        public final Production production;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public LRItem(int dot, Production production) {
            this.dot = dot;
            this.production = production;
        }
        //</editor-fold>

        @Override
        public String toString () {
            return production.toString() + "(" + dot + ")";
        }
    }

    public static class LRState {
        public final int number;
        public final List <LRItem> items;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public LRState(int number, List<LRItem> items) {
            this.number = number;
            this.items = items;
        }
        //</editor-fold>

        @Override
        public String toString () {
            return "[" + number + "]\n" + items;
        }
    }



    /* Make Parser */

    public static ParseMachine getParser (GrammarUtils.GrammarInfo grammarInfo) {
        // Grammar.
        Grammar grammar = grammarInfo.grammar;
        List <Production> productions = grammar.productionRules();

        // Lists of States.
        ArrayList <LRState> states = new ArrayList<>();
        ArrayList <LRState> newStates = new ArrayList<>();

        // Lists of Actions.
        ArrayList <ShiftAction> shiftActions = new ArrayList<>();
        ArrayList <ReduceAction> reduceActions = new ArrayList<>();

        // Starting State.
        ArrayList <LRItem> items = new ArrayList<>();
        items.add(new LRItem(0, productions.get(0)));
        states.add(new LRState(0, closure(items, grammarInfo)));

        // Accepting state.
        int accept = 0;

        // Loop.
        int i = 0;
        for (;;) {
            // Process new states.
            int k = states.size();
            while (i < k) {
                // Current State.
                LRState state = states.get(i);

                // Set of shift symbols.
                HashSet <Symbol> shiftsymbols = new HashSet<>();

                // Iterate over each item
                for (LRItem item : state.items) {
                    // Check if Reduction or Shift item.
                    if (item.dot < item.production.production().size()) {
                        // Shift.
                        shiftsymbols.add(item.production.production().get(item.dot));
                    } else {
                        // Reduction.
                        for (Symbol symbol : grammarInfo.follow.get(item.production.variable())) {
                            reduceActions.add(new ReduceAction(state.number, symbol, item.production));
                        }
                    }
                }

                // Process shift symbols.
                for (Symbol symbol : shiftsymbols) {
                    LRState newState = insert(shift(state.items, symbol, grammarInfo), states, newStates);
                    ShiftAction act = new ShiftAction(state.number, symbol, newState.number);
                    shiftActions.add(act);
                    // if (symbol == eofSymbol) ...
                }

                // Iterate.
                ++i;
            }

            // If no new states are added then exit.
            if (newStates.isEmpty()) break;

            // Add new states to the list and continue.
            states.addAll(newStates);
            newStates.clear();
        }

        for (LRState s : states) {
            //System.out.println(s);
        }

        System.out.println("States: " + states.size());

        return new ParseMachine(accept, shiftActions, reduceActions);
    }

    // Note about Duplicate Items:
    //
    //   ...
    //

    // NOTE: Modifies the provided list.
    // NOTE: Duplicate items are never added.
    private static List <LRItem> closure (List <LRItem> items, GrammarUtils.GrammarInfo info) {
        // Grammar.
        Grammar grammar = info.grammar;
        Map <Symbol, List <Production>> productionMap = grammar.productionMap();

        // Keep track of new items added.
        HashSet <Symbol> added = new HashSet<>();

        // Add new items to list.
        int i = 0;
        while (i < items.size()) {
            LRItem item = items.get(i);

            int dot = item.dot;
            Production production = item.production;

            if (dot < production.production().size()) {
                Symbol symbol = production.production().get(dot);

                if (symbol.isVariable() && !added.contains(symbol)){
                    added.add(symbol);

                    //System.out.println(symbol);
                    for (Production prod : productionMap.get(symbol)) {

                        items.add(new LRItem(0, prod));
                    }
                }
            }
            ++i;
        }

        // Return list.
        return items;
    }

    // NOTE: Does not modify the provided list. Creates a new list.
    private static List <LRItem> shift (List <LRItem> items, Symbol symbol, GrammarUtils.GrammarInfo info) {
        // List of new items.
        ArrayList <LRItem> newItems = new ArrayList<>();

        // Add Items.
        for (LRItem item : items) {
            int dot = item.dot;
            Production production = item.production;

            if (dot < production.production().size()) {
                if (production.production().get(dot).equals(symbol)){
                    newItems.add(new LRItem(dot+1, production));

                }
            }
        }

        // Return the closure of the new list.
        return closure(newItems, info);
    }

    // NOTE: If a state with the matching items exists in states or newStates, then that state is returned.
    //       Otherwise, a new state is created and added to newStates.
    private static LRState insert (List <LRItem> items, List <LRState> states, List <LRState> newStates) {
        // Check in first list.
        for (LRState state : states) {
            if (compare(items, state.items)) {
                return state;
            }
        }

        // Check in second list.
        for (LRState state : newStates) {
            if (compare(items, state.items)) {
                return state;
            }
        }

        // New State.
        LRState state = new LRState(states.size() + newStates.size(), items);
        newStates.add(state);
        return state;
    }

    // NOTE: Items do not need to be in the same order, just a 1-1 correspondence.
    private static boolean compare (List <LRItem> X, List <LRItem> Y) {
        // Trival Case.
        if (X.size() != Y.size()) return false;

        X:for (LRItem x : X)  {
            int dot = x.dot;
            Production prod = x.production;
            Y:for (LRItem y : Y) {
                if (dot == y.dot) {
                    // Same dot.
                    if (prod.equals(y.production)) {
                        // Same production.
                        // Same item!
                        continue X;
                    }
                }
            }
            // No match for x found in Y.
            // Unique items.
            return false;
        }

        return true;
    }
}
