package net.ammy.precompile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Translator {

    private Grammar grammar;
    private Map <Production, List <Item>> dictionary;

    public Translator (Grammar _grammar) {
        grammar = _grammar;
        dictionary = new HashMap<>();

        build();
    }

    public List <String> translate (Token root) {
        ArrayList <String> translation = new ArrayList<>();
        expand(root, translation);
        return translation;
    }


    // Expands a node into the translation.
    private void expand (Token token, List <String> translation) {
        if (token.isDerivation()) {
            // Derivation
            // Get Dictionary Entry.
            List <Item> entry = dictionary.get(token.productionRule());
            if (entry == null) return;

            // Concatenation Buffer.
            StringBuilder cbuffer = null;

            // Concatenation Level;
            int clevel = 0;

            // Apply each entry item.
            for (Item item : entry) {
                switch (item.type) {
                    case ITEM_STRING: {
                        if (clevel > 0 && cbuffer != null) {
                            cbuffer.append(item.value);
                        } else {
                            translation.add(item.value);
                        }
                        break;
                    }
                    case ITEM_SINGLE_CONCAT: {
                        throw new UnsupportedOperationException("Single concatenation not supported");
                    }
                    case ITEM_BEGIN_CONCAT: {
                        if (clevel == 0)
                            cbuffer = new StringBuilder();
                        ++clevel;
                        break;
                    }
                    case ITEM_END_CONCAT: {
                        --clevel;
                        if (clevel < 0)
                            throw new RuntimeException("Invalid Translation I");
                        if (clevel == 0 && cbuffer != null) {
                            translation.add(cbuffer.toString());
                            cbuffer = null;
                        }
                        break;
                    }
                    case ITEM_RECURSION: {
                        int id = item.rvalue;
                        if (id < 0 || id > token.derivation().size())
                            throw new RuntimeException("Invalid Translation II");

                        Token sub = token.derivation().get(id);

                        if (clevel > 0 && cbuffer != null) {
                            expand(sub, cbuffer);
                        } else {
                            expand(sub, translation);
                        }

                        break;
                    }
                    default:
                        throw new RuntimeException("Invalid Translation II");
                }
            }

            if (clevel != 0) throw new RuntimeException("Invalid Translation I");

        } else {
            // Token.
            Object lexeme = token.lexeme();
            if (lexeme != null) {
                translation.add(lexeme.toString());
            }
        }
    }

    // Expands a node and appends all strings.
    private void expand (Token token, StringBuilder translation) {
        if (token.isDerivation()) {
            // Derivation
            // Get Dictionary Entry.
            List <Item> entry = dictionary.get(token.productionRule());
            if (entry == null) return;

            // Concatenation Level;
            int clevel = 0;

            // Apply each entry item.
            for (Item item : entry) {
                switch (item.type) {
                    case ITEM_STRING: {
                        translation.append(item.value);
                        break;
                    }
                    case ITEM_SINGLE_CONCAT: {
                        throw new UnsupportedOperationException("Single concatenation not supported");
                    }
                    case ITEM_BEGIN_CONCAT: {
                        ++clevel;
                        break;
                    }
                    case ITEM_END_CONCAT: {
                        --clevel;
                        if (clevel < 0)
                            throw new RuntimeException("Invalid Translation I");
                        break;
                    }
                    case ITEM_RECURSION: {
                        int id = item.rvalue;
                        if (id < 0 || id > token.derivation().size())
                            throw new RuntimeException("Invalid Translation II");
                        Token sub = token.derivation().get(id);
                        expand(sub, translation);
                        break;
                    }
                    default:
                        throw new RuntimeException("Invalid Translation II");
                }
            }

            if (clevel != 0) throw new RuntimeException("Invalid Translation I");

        } else {
            // Token.
            Object lexeme = token.lexeme();
            if (lexeme != null) {
                translation.append(lexeme.toString());
            }
        }
    }



    // Build the dictionary.
    private void build () {
        // Semantic Values.
        Map <Production, List <String>> semanticsMap = grammar.semanticsMap();

        // Build.
        for (Production production : semanticsMap.keySet()) {
            // Semantic strings.
            List <String> strings = semanticsMap.get(production);

            // Only care about first string.
            if (strings.isEmpty()) continue;
            String string = strings.get(0);

            // Tokenize.
            String[] svalues = string.split("  *");

            // New entry.
            ArrayList <Item> entry = new ArrayList<>();

            // Build.
            for (String svalue : svalues) {
                if (svalue.startsWith("%")) {
                    // Recursion.
                    int len = svalue.length();
                    int occurence = 1;
                    String symbol;

                    // Check occurence number.
                    if (svalue.charAt(len-2) == '.') {
                        char c = svalue.charAt(len-1);
                        if ('0' <= c && c <= '9') {
                            occurence = c - '0';
                            symbol = svalue.substring(1, len-2);
                        } else {
                            throw new RuntimeException("!!!!! Invalid Translation");
                        }
                    } else {
                        symbol = svalue.substring(1);
                    }

                    //Find symbol in production.
                    List <Symbol> symbols = production.production();
                    for (int i = 0; i < symbols.size(); ++i) {
                        if (symbols.get(i).text().equals(symbol)) {
                            --occurence;
                            if (occurence == 0) {
                                entry.add(new Item(ITEM_RECURSION, i));
                                break;
                            }
                        }
                    }
                } else if (svalue.startsWith("'")) {
                    // String.
                    entry.add(new Item(ITEM_STRING, svalue.substring(1)));

                } else if (svalue.equals("+")) {
                    // Single Concatenation.
                    entry.add(new Item(ITEM_SINGLE_CONCAT, null));

                } else if (svalue.equals("<")) {
                    // Begin Group Concatenation.
                    entry.add(new Item(ITEM_BEGIN_CONCAT, null));

                } else if (svalue.equals(">")) {
                    // End Group Concatenation.
                    entry.add(new Item(ITEM_END_CONCAT, null));

                } else {
                    // String item
                    entry.add(new Item(ITEM_STRING, svalue));
                }
            }

            // Add entry to dictionary.
            dictionary.put(production, entry);
        }
    }


    public static final int
            ITEM_STRING = 0,
            ITEM_SINGLE_CONCAT = 1,
            ITEM_BEGIN_CONCAT = 2,
            ITEM_END_CONCAT = 3,
            ITEM_RECURSION = -1;

    public static class Item {
        public final int type;
        public final int rvalue;
        public final String value;

        //<editor-fold defaultstate="collapsed" desc="constructors">
        public Item(int type, String value) {
            this.type = type;
            this.rvalue = 0;
            this.value = value;
        }

        public Item(int type, int rvalue) {
            this.type = type;
            this.rvalue = rvalue;
            this.value = null;
        }
        //</editor-fold>
    }

}
