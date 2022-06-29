package net.ammy.precompile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a context-free grammar.
 *
 */
public class Grammar {

    /* Fields */

    // Symbols, ordered by their indices.
    private List <Symbol> symbols;

    // Production Rules, ordered by their indices.
    private List <Production> productions;

    // A map from symbol name to symbol object.
    private Map <String, Symbol> symbolMap;

    // A map from symbol to list of associated productions.
    private Map <Symbol, List<Production>> productionMap;

    // A map from production to list of semantic strings for that production.
    private Map <Production, List <String>> semanticMap;

    /* Constructor */

    public Grammar (File file) throws IOException {
        loadSymbols(file);
        loadProductions(file);
    }

    /* Operations */

    public List <Symbol> symbols () {
        return symbols;
    }

    public List <Production> productionRules () {
        return productions;
    }

    public Map <String, Symbol> symbolMap () {
        return symbolMap;
    }

    public Map <Symbol, List<Production>> productionMap () {
        return productionMap;
    }

    public Map <Production, List <String>> semanticsMap () {
        return semanticMap;
    }

    public Symbol EOF () {
        for (Symbol symbol : symbols)
            if (symbol.type() == SymbolType.EOF)
                return symbol;
        return null;
    }

    /* Overrides */

    @Override
    public String toString () {
        //TODO: add a toString().
        return "<Grammar>\n\t" + symbols + "\n\t" + productions + "\n\t"  + semanticMap + "\n</Grammar>";
    }

    /* Loading Methods */

    private void loadSymbols (File file) throws IOException {
        symbols = new ArrayList<>();
        symbolMap = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            int index = 0;

            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("|") || line.isEmpty()) {
                    continue;
                }

                if (!line.startsWith("@")){
                    continue;
                }

                String[] symbolNames = line.substring(1).split("  *");

                SymbolType symbolType = SymbolType.getSymbolType(symbolNames[0]);

                for (int i = 1; i < symbolNames.length; ++i) {
                    String symbolName = symbolNames[i];

                    Symbol symbol = new Symbol(index++, symbolType, symbolName, this);
                    symbols.add(symbol);
                    symbolMap.put(symbol.text(), symbol);
                }

            }
        }

        symbols = Collections.unmodifiableList(symbols);
        symbolMap = Collections.unmodifiableMap(symbolMap);
    }

    private void loadProductions (File file) throws IOException {
        productions = new ArrayList<>();
        productionMap = new HashMap<>();
        semanticMap = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int index = 0;

            // Last Parsed Production.
            Production production = null;

            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();

                // Skip over comment.
                if (line.startsWith("|") || line.isEmpty()) {
                    continue;
                }

                // Skip over symbol lines
                if (line.startsWith("@")) {
                    continue;
                }

                // Parse semantic line.
                if (line.startsWith(";")) {
                    if (production == null) throw new RuntimeException("!!!!! Invalid Grammar File.");

                    line = line.substring(1).trim();
                    List <String> semantics;

                    if (semanticMap.containsKey(production)) {
                        semantics = semanticMap.get(production);
                    } else {
                        semantics = new ArrayList<>();
                        semanticMap.put(production, semantics);
                    }

                    semantics.add(line);

                    continue;
                }

                String[] productionSymbols = line.split("  *");

                Symbol variable = symbolMap.get(productionSymbols[0]);
                ArrayList <Symbol> derivations = new ArrayList<>();

                if (variable == null) throw new RuntimeException("-- " + productionSymbols[0]);

                if (productionSymbols.length >= 3) {
                    if (!productionSymbols[1].equals("::")) {
                        throw new RuntimeException("!!!!! Invalid Grammar File: " + line);
                    }
                    for (int i = 2; i < productionSymbols.length; ++i) {
                        Symbol s = symbolMap.get(productionSymbols[i]);
                        if (s == null) {
                            System.out.println(line);
                            throw new RuntimeException("-- " + productionSymbols[i]);
                         }
                        derivations.add(s);
                    }
                }

                production = new Production(index++, variable, Collections.unmodifiableList(derivations));
                productions.add(production);

                List <Production> list;
                if (productionMap.containsKey(production.variable())) {
                    list = productionMap.get(production.variable());
                } else {
                    list = new ArrayList<>();
                    productionMap.put(production.variable(), list);
                }
                list.add(production);
            }
        }

        symbols = Collections.unmodifiableList(symbols);
        symbolMap = Collections.unmodifiableMap(symbolMap);

        productions = Collections.unmodifiableList(productions);

        for (Symbol s : productionMap.keySet()) {
            List <Production> list = productionMap.get(s);
            productionMap.put(s, Collections.unmodifiableList(list));
        }

        for (Production production : semanticMap.keySet()) {
            List <String> list = semanticMap.get(production);
            semanticMap.put(production, Collections.unmodifiableList(list));
        }

        productionMap = Collections.unmodifiableMap(productionMap);
        semanticMap = Collections.unmodifiableMap(semanticMap);
    }

}
