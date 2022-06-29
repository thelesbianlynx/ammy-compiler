package net.ammy.precompile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class ParserUtils {

    /* Classes */

    public static class LRItem {
        public final int dot;
        public final Production production;
        public final Symbol lookahead;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public LRItem(int dot, Production production, Symbol lookahead) {
            this.dot = dot;
            this.production = production;
            this.lookahead = lookahead;
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

    public static class ShiftAction {
        public final int inState;
        public final Symbol symbol;
        public final int toState;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public ShiftAction(int inState, Symbol symbol, int toState) {
            this.inState = inState;
            this.symbol = symbol;
            this.toState = toState;
        }
        //</editor-fold>

        @Override
        public String toString () {
            return "s " + inState + " " + symbol + " " + toState;
        }
    }

    public static class ReduceAction {
        public final int inState;
        public final Symbol symbol;
        public final Production reduction;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public ReduceAction(int inState, Symbol symbol, Production reduction) {
            this.inState = inState;
            this.symbol = symbol;
            this.reduction = reduction;
        }
        //</editor-fold>

        @Override
        public String toString () {
            return "r " + inState + " " + symbol + " " + reduction.index();
        }
    }

    public static class ParseMachine {
        public final int accept;
        public final List <ShiftAction> shiftActions;
        public final List <ReduceAction> reduceActions;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public ParseMachine(int accept, List<ShiftAction> shiftActions, List<ReduceAction> reduceActions) {
            this.accept = accept;
            this.shiftActions = shiftActions;
            this.reduceActions = reduceActions;
        }
        //</editor-fold>

        @Override
        public String toString () {
            StringBuilder ss = new StringBuilder();
            ss.append("a ").append(accept).append("\n");
            for (ShiftAction s : shiftActions) ss.append(s).append("\n");
            for (ReduceAction r : reduceActions) ss.append(r).append("\n");
            return ss.toString();
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
        items.add(new LRItem(0, productions.get(0), grammar.EOF()));
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
                        //for (Symbol symbol : grammarInfo.follow.get(item.production.variable())) {
                        reduceActions.add(new ReduceAction(state.number, item.lookahead, item.production));
                        //}
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
        System.out.println("Actions: " + (shiftActions.size() + reduceActions.size()));


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
        //HashSet <Symbol> added = new HashSet<>();

        // Add new items to list.
        int i = 0;
        while (i < items.size()) {
            LRItem item = items.get(i);

            int dot = item.dot;
            Production production = item.production;

            if (dot < production.production().size()) {
                Symbol symbol = production.production().get(dot);

                if (symbol.isVariable()){
                    Set <Symbol> followset = follow(item, info);

                    for (Production prod : productionMap.get(symbol)) {
                        for (Symbol follow : followset) {
                            addUnique(new LRItem(0, prod, follow), items);
                        }
                    }
                }
            }
            ++i;
        }

        // Return list.
        return items;
    }

    // NOTE:
    private static Set <Symbol> follow (LRItem item, GrammarUtils.GrammarInfo info) {
        // Follow set to be constructed.
        HashSet <Symbol> followset = new HashSet<>();

        // Item's Production Rule.
        Production production = item.production;

        // Index of Symbol Directly after the symbol after the dot.
        //  i.e. the symbol that follows the current symbol
        int i = item.dot + 1;
        for (;;) {

            // End of production.
            // Add Lookahead and break.
            if (i >= production.production().size()) {
                followset.add(item.lookahead);
                break;
            }

            // Follow Symbol.
            Symbol follow = production.production().get(i);

            // Follow symbol is terminal.
            // Add this symbol and break.
            if (!follow.isVariable()) {
                followset.add(follow);
                break;
            }

            // Follow must be variable.
            // Add all elements of the follow's first set.
            for (Symbol sym : info.first.get(follow))
                followset.add(sym);

            // If the follow symbol is not nullable, then break.
            // If the follow symbol is nullable, then we have to consider
            //  the follow's follow symbol as well.
            if (!info.nullable.contains(follow))
                break;

            ++i;
        }

        return followset;
    }

    // NOTE: Adds new item to list only if an identical item is not already somewhere in the list.
    // NOTE: Always adds to end of the list.
    private static void addUnique (LRItem newitem, List <LRItem> items) {
        int dot = newitem.dot;
        Production prod = newitem.production;
        Symbol lookahead = newitem.lookahead;

        // Check each item.
        for (LRItem item : items) {
            if (dot == item.dot) {
                // Same dot.
                if (prod.equals(item.production)) {
                    // Same production.
                    if (lookahead.equals(item.lookahead)) {
                        // Same lookahead.
                        // Same item!
                        //  -> Don't add!
                        return;
                    }
                }
            }
        }

        items.add(newitem);
    }

    // NOTE: Does not modify the provided list. Creates a new list.
    private static List <LRItem> shift (List <LRItem> items, Symbol symbol, GrammarUtils.GrammarInfo info) {
        // List of new items.
        ArrayList <LRItem> newItems = new ArrayList<>();

        // Add Items.
        for (LRItem item : items) {
            int dot = item.dot;
            Production production = item.production;
            Symbol lookahead = item.lookahead;

            if (dot < production.production().size()) {
                if (production.production().get(dot).equals(symbol)){
                    newItems.add(new LRItem(dot+1, production, lookahead));

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
            Symbol lookahead = x.lookahead;
            Y:for (LRItem y : Y) {
                if (dot == y.dot) {
                    // Same dot.
                    if (prod.equals(y.production)) {
                        // Same production.
                        if (lookahead.equals(y.lookahead)) {
                            // Same lookahead.
                            // Same item!
                            continue X;
                        }
                    }
                }
            }
            // No match for x found in Y.
            // Unique items.
            return false;
        }

        return true;
    }


    public static void writeParser (ParseMachine parseMachine, File parseFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(parseFile))) {
            writer.append(parseMachine.toString());
        } catch (IOException x) {
            System.err.println("Failed to write parser cache: " + x.getMessage());
        }
    }

    public static ParseMachine readParser (File parseFile, Grammar grammar) {
        int accept = 0;
        List <ShiftAction> shiftActions = new ArrayList<>();
        List <ReduceAction> reduceActions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(parseFile))) {


            String token;
            while ((token = readToken(reader)) != null) {

                switch (token) {
                    case "a": {
                        token = readToken(reader);
                        accept = Integer.parseInt(token);
                        break;
                    }
                    case "s": {
                        token = readToken(reader);
                        int s1 = Integer.parseInt(token);
                        token = readToken(reader);
                        Symbol sym = grammar.symbolMap().get(token);
                        token = readToken(reader);
                        int s2 = Integer.parseInt(token);

                        ShiftAction s = new ShiftAction(s1, sym, s2);
                        shiftActions.add(s);
                        break;
                    }
                    case "r": {
                        token = readToken(reader);
                        int s1 = Integer.parseInt(token);
                        token = readToken(reader);
                        Symbol sym = grammar.symbolMap().get(token);
                        token = readToken(reader);
                        Production prod = grammar.productionRules().get(Integer.parseInt(token));

                        ReduceAction r = new ReduceAction(s1, sym, prod);
                        reduceActions.add(r);
                        break;
                    }
                }

            }



//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] args = line.split("  *");
//                switch (args[0]) {
//                    case "a":
//                        accept = Integer.parseInt(args[1]);
//                        break;
//                    case "s":
//                        ShiftAction s = new ShiftAction(
//                                Integer.parseInt(args[1]),
//                                grammar.symbolMap().get(args[2]),
//                                Integer.parseInt(args[3]));
//                        shiftActions.add(s);
//                        break;
//                    case "r":
//                        ReduceAction r = new ReduceAction(
//                                Integer.parseInt(args[1]),
//                                grammar.symbolMap().get(args[2]),
//                                grammar.productionRules().get(Integer.parseInt(args[3])));
//                        reduceActions.add(r);
//                        break;
//                }
//            }

        } catch (IOException x) {
            System.err.println("Failed to read parser cache: " + x.getMessage());
        }

        return new ParseMachine(accept, shiftActions, reduceActions);
    }

    private static String readToken (BufferedReader reader) throws IOException {
        // String buffer.
        int i = 0;
        char[] buffer = new char[256];

        // Character buffer.
        int c;

        // Read past white-space.
        X:for (;;) {
            c = reader.read();
            Y:switch (c) {
                case -1:
                    return null;
                case ' ':
                case '\t':
                case '\n':
                    break Y;
                default:
                    break X;
            }
        }

        // Read Characters
        X:for (;;) {
            buffer[i] = (char) c;
            ++i;

            c = reader.read();
            Y:switch (c) {
                case -1:
                case ' ':
                case '\t':
                case '\n':
                    break X;
            }
        }

        return new String(buffer, 0, i);
    }
}
