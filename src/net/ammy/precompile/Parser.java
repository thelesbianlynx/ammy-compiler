package net.ammy.precompile;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.ammy.precompile.ParserUtils.*;

/**
 * Parses a sequence of tokens into a derivation tree.
 *
 */
public class Parser {

    /* Fields */

    // Grammar.
    private Grammar grammar;

    // Parse Machine.
    private ParseMachine dfa;

    // Parse Action, organized by state and symbol.
    private Map <Integer, Map <Symbol, ShiftAction>> shiftActions;
    private Map <Integer, Map <Symbol, ReduceAction>> reduceActions;


    /* Constructor */

    public Parser (Grammar _grammar, ParseMachine _dfa) {
        grammar = _grammar;
        dfa = _dfa;

        shiftActions = new HashMap<>();
        reduceActions = new HashMap<>();


        //<editor-fold defaultstate="collapsed" desc="Assemble Machine">

        // Largest state
        int max = 0;

        // Shift Actions.
        for (ShiftAction shiftAction : dfa.shiftActions) {
            max = Math.max(max, shiftAction.inState);
            // Find State Map.
            Map <Symbol, ShiftAction> map;
            if (!shiftActions.containsKey(shiftAction.inState)) {
                map = new HashMap<>();
                shiftActions.put(shiftAction.inState, map);
            } else {
                map = shiftActions.get(shiftAction.inState);
            }

            // Insert Action.
            if (map.containsKey(shiftAction.symbol)) {
                // Shift-Shift Conflict.
                System.err.println("!!!! Shift-Shift Conflict: "
                        + shiftAction + " / " + map.get(shiftAction.symbol));
            } else {
                map.put(shiftAction.symbol, shiftAction);
            }
        }

        // Shift Actions.
        for (ReduceAction reduceAction : dfa.reduceActions) {
            max = Math.max(max, reduceAction.inState);
            // Find State Map.
            Map <Symbol, ReduceAction> map;
            if (!reduceActions.containsKey(reduceAction.inState)) {
                map = new HashMap<>();
                reduceActions.put(reduceAction.inState, map);
            } else {
                map = reduceActions.get(reduceAction.inState);
            }

            // Insert Action.
            if (map.containsKey(reduceAction.symbol)) {
                // Reduce-Reduce Conflict.
                System.err.println("!!!! Reduce-Reduce Conflict: "
                        + reduceAction + " / " + map.get(reduceAction.symbol));
            } else {
                map.put(reduceAction.symbol, reduceAction);
            }
        }

        //Check for Shift-Reduce Conflicts.
        for (int i = 0; i <= max; ++i) {
            Map <Symbol, ShiftAction> smap = shiftActions.get(i);
            Map <Symbol, ReduceAction> rmap = reduceActions.get(i);
            if (smap != null && rmap != null) {
                for (Symbol s : smap.keySet()) {
                    if (rmap.containsKey(s)) {
                        // Shift-Reduce Conflict.
                        System.err.println("!!!! Shift-Reduce Conflict: "
                                + smap.get(s) + " / " + rmap.get(s));
                    }
                }
            }
        }
        //</editor-fold>


    }


    /* Parse Tokens */

    // Parser Stack Item.
    private static class Item {
        final int state;
        final Token token;

        //<editor-fold defaultstate="collapsed" desc="constructor">
        public Item(int state, Token token) {
            this.state = state;
            this.token = token;
        }
        //</editor-fold>
    }

    public Token parse (List <Token> tokens) {
        // Item Stack.
        Deque <Item> stack = new ArrayDeque<>();

        for (Token token : tokens) {
            // Try to reduce stack.
            tryReductions(stack, token.symbol());

            // Shift on to stack.
            shift(stack, token);
        }

        // Reduce Starting Rule.

        return reduce(stack, grammar.productionRules().get(0));
    }


    /* Stack Operations */

    private void shift (Deque <Item> stack, Token token) {
        // Current state.
        int state = stack.isEmpty() ? 0 : stack.getFirst().state;

        // Shift Action.
        Map <Symbol, ShiftAction> m = shiftActions.get(state);
        if (m == null) System.out.println(token + "," + token.location());
        ShiftAction shiftAction = m.get(token.symbol());

        // Invalid Shift.
        if (shiftAction == null) {
            System.err.println("!!!! Unexpected " + token + " @ " + token.location());

            // Error Recovery.
            //  ...

            return;
        }

        // Shift to top of stack.
        state = shiftAction.toState;
        Item item = new Item(state, token);
        stack.push(item);
    }

    private void tryReductions (Deque <Item> stack, Symbol lookahead) {
        for (;;) {
            // Current state.
            int state = stack.isEmpty() ? 0 : stack.getFirst().state;

            // Shift Action.
            ReduceAction reduceAction;

            Map <Symbol, ReduceAction> map = reduceActions.get(state);
            if (map == null) break;

            reduceAction = map.get(lookahead);
            if (reduceAction == null) break;

            // Reduce.
            Token token = reduce(stack, reduceAction.reduction);

            //Shift new Token.
            shift(stack, token);
        }
    }

    private Token reduce (Deque <Item> stack, Production rule) {
        // Tokens popped as part of derivation.
        List <Token> derivation = new ArrayList<>();

        // Symbols making up production rule.
        List <Symbol> production = rule.production();

        // Pop tokens from stack.
        for (int i = production.size()-1; i >= 0; --i) {
            Symbol symbol = production.get(i);
            Item item = stack.pop();
            Token tok = item.token;
            if (tok.symbol() == symbol) {
                derivation.add(tok);
            } else {
                throw new RuntimeException("Invalid Reduction");
            }
        }

        // Reverse the order.
        Collections.reverse(derivation);

        // Assemble and return new token.
        return new Token(rule.variable(), rule, derivation);
    }


    /*

            if (reduceAction != null) {
                // Pop tokens from stack.
                Production production = reduceAction.reduction;

                // Get Current State.
                state = stack.isEmpty() ? 0 : stack.getFirst().state;

                // Push new token.
                ShiftAction shiftAct = shiftActions.get(state).get(production.variable());
                if (shiftAct == null) throw new RuntimeException("Invalid Reduction II");

                state = shiftAct.toState;
                Item item = new Item(state, );
                stack.push(item);

                continue;
            }

            // Neither shift nor reduce.


    */

}
