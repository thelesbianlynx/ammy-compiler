package net.ammy.precompile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs lexical analysis on a program.
 *
 */
public class Scanner {

    /* Fields */

    //Grammar//
    private Grammar grammar;
    private Map <String, Symbol> symbolMap;

    //Output Symbols//
    private Symbol idSymbol;        //Identifier
    private Symbol intSymbol;       //Integer Literal
    private Symbol byteSymbol;      //Byte(Char) Literal
    private Symbol shortSymbol;     //Short Literal
    private Symbol longSymbol;      //Long Literal.
    private Symbol floatSymbol;     //Float Literal.
    private Symbol doubleSymbol;    //Double Literal.
    private Symbol strSymbol;       //String Literal.
    private Symbol chrSymbol;       //Character Literal.
    private Symbol eofSymbol;       //End of File.

    //Keyword Map//
    private Map <String, Symbol> keywordMap;

    //Operator Map//
    private Map <String, Symbol> operatorMap;

    //Operator parsing states//
    private State operatorStart;

    //Operator parsing tools// 17 > | 11 -
    private static final String operatorSymbols = "`~!@#$%^&*()-+=,.<>?/:;[]{}";
    private static final byte REJECT = 0, ACCEPT = 1, PARTIAL = 2;
    private static class State {
        int[] stateInfo;
        State[] stateNext;
        State () {
            stateInfo = new int[operatorSymbols.length()];
            stateNext = new State[operatorSymbols.length()];
        }
    }


    //Scanner Stream//
    int next;
    InputStream instream;

    //Scanner Location//
    String filename;
    int line, col;


    /* Constructor */

    public Scanner (Grammar _grammar) {
        grammar = _grammar;
        symbolMap = grammar.symbolMap();
        idSymbol = null;
        intSymbol = null;
        shortSymbol = null;
        longSymbol = null;
        floatSymbol = null;
        doubleSymbol = null;
        eofSymbol = null;
        keywordMap = new HashMap<>();
        operatorMap = new HashMap<>();
        initialize();
    }


    /* Scan File */

    public List <Token> scan (File scanfile) {
        ArrayList <Token> tokens = new ArrayList<>();

        try {
            // Open File.
            col = 1;
            line = 1;
            filename = scanfile.getName();
            instream = new FileInputStream(scanfile);
            nextchar();

            // Read entire file.
            L:for (;;) {

                // End of file.
                if (next == -1) break;

                // Whitespace.
                if (ws(next)) {
                    nextchar();
                    continue;
                }

                // Comment Line.
                if (next == '|') {
                    for (;;) {
                        nextchar();
                        if (next == '\n') break;
                        if (next == -1) break L;
                    }
                    nextchar();
                    continue;
                }

                // Identifier or Keyword.
                if (text(next)) {
                    Token tok = textToken();
                    tokens.add(tok);
                    continue;
                }

                // Number Literal.
                if (num(next) >= 0) {
                    Token tok = numToken();
                    tokens.add(tok);
                    continue;
                }

                // Symbol.
                if (sym(next) >= 0) {
                    symTokens(tokens);
                    continue;
                }

                // String or Character Literal.
                if (next == '"' || next == '\'') {
                    Token tok = strToken();
                    tokens.add(tok);
                    continue;
                }

                //Illegal Character.
                System.err.println("!!! Illegal Character: '" + (char) next
                        + "' @ " + currentLocation().toString());
                tokens.add(null);
                nextchar();

            }

            // Add End-Of-File Token.
            tokens.add(new Token(eofSymbol));

        } catch (FileNotFoundException x) {
            // File Not Found.
            System.out.println("!!! FILE '" + filename + "' DOES NOT EXISTS !!!");
            tokens = null;
        } catch (IOException x) {
            // File Read Error.
            System.out.println("!!! FILE '" + filename + "' COULD NOT BE READ!!!");
            tokens = null;
        }

        // Close File.
        try {
            instream.close();
        } catch (IOException x) {
            // This should never catch.
        }

        // Cleanup.
        filename = null;
        instream = null;

        return tokens;
    }

    // Reads the next character from the input stream. //
    //  * If stream is at end of file, ch is assigned -1.
    private void nextchar() throws IOException {
        next = instream.read();

        if (next > 127){
            //UTF-8 MULTI-BYTE.
                //...
        }

        ++col;
        if (next == '\n'){
            ++line;
            col = 1;
        }
    }

    // Returns the current location of the Scanner. /
    private Location currentLocation () {
        return new Location(filename, line, col);
    }


    /* Scan Specific Tokens */

    // For Each Token Method:
    //  * On Entering, 'next' must be the first character in the token.
    //  * On Exiting, 'next' will be the first character not in the token.
    //  * Returning null indicates an invalid token.

    // Identifier or Keyword //
    private Token textToken () throws IOException {
        // Symbol.
        Symbol symbol = idSymbol;
        // Text.
        StringBuilder text = new StringBuilder();
        // Location.
        Location location = currentLocation();

        // Scan Text.
        for (;;) {
            if (!(text(next) || num(next) >= 0)) break;

            text.appendCodePoint(next);
            nextchar();
        }

        // Check if text matches keyword.
        String lexeme = text.toString();
        if (keywordMap.containsKey(lexeme)) {
            symbol = keywordMap.get(lexeme);
        }

        return new Token(symbol, lexeme, location);
    }

    // Number Literal (integer, decimal, etc.) //
    private Token numToken () throws IOException {
        // Symbol.
        Symbol symbol = intSymbol;
        // Location.
        Location location = currentLocation();
        // Literal String, to be parsed as the corresponding value type.
        StringBuilder str = new StringBuilder();

        // Leading 0.
        if (next == '0') {
            str.appendCodePoint(next);
            nextchar();

            // Hexadecimal Literal.
            if (next == 'x'){
                nextchar();
                //...

                return null;
            }

            // Binary Literal.
            if (next == 'b') {
                nextchar();
                //...

                return null;
            }
        }

        // Integer.
        while (num(next) != -1) {
            str.appendCodePoint(next);
            nextchar();
        }

        // Short Suffix.
        if (next == 's' || next == 'S') {
            nextchar();
            symbol = shortSymbol;
        }

        // Long Suffix.
        else if (next == 'l' || next == 'L') {
            nextchar();
            symbol = longSymbol;
        }

        // Parse Decimal Component.
        else {

            // Decimal Point.
            if (next == '.') {
                str.appendCodePoint(next);
                nextchar();
                symbol = doubleSymbol;

                while (num(next) != -1) {
                    str.appendCodePoint(next);
                    nextchar();
                }
            }

            // Exponent.
            if (next == 'e' || next == 'E') {
                str.appendCodePoint(next);
                nextchar();
                symbol = doubleSymbol;

                // Allowed: '+' or '-'.
                if (next == '+' || next == '-') {
                    str.appendCodePoint(next);
                    nextchar();
                }

                while (num(next) != -1) {
                    str.appendCodePoint(next);
                    nextchar();
                }
            }

            // Float Suffix.
            if (next == 'f' || next == 'F') {
                nextchar();
                symbol = floatSymbol;
            }

        }

        //Parse String//
        Object value;
        try {
            if (symbol == intSymbol) {
                value = Integer.valueOf(str.toString());
            } else if (symbol == shortSymbol) {
                value = Short.valueOf(str.toString());
            } else if (symbol == longSymbol) {
                value = Long.valueOf(str.toString());
            } else if (symbol == floatSymbol) {
                value = Float.valueOf(str.toString());
            } else if (symbol == doubleSymbol) {
                value = Double.valueOf(str.toString());
            } else {
                // This should be unreachable.
                return null;
            }
        } catch (NumberFormatException x) {
            System.err.println("!!!! Malformed Literal or Literal Too Large: '" + str.toString() + "'");
            return null;
        }

        return new Token(symbol, str.toString(), location);
    }

    // Operator or Symbol Token //
    private void symTokens (List <Token> tokens) throws IOException {
        // Location.
        Location location = currentLocation();
        // Literal String, to be parsed as the corresponding value type.
        StringBuilder str = new StringBuilder();

        // Build symbol string.
        for (;;) {
            if (sym(next) < 0) break;

            str.appendCodePoint(next);
            nextchar();
        }

        // Lookup in symbol map.
        // Start by looking up full string then by looking up prefixes of the string.
        String rest = str.toString();
        M:while (!rest.isEmpty()) {
            //System.out.println(rest);
            for (int i = rest.length(); i > 0; --i) {
                String sym = rest.substring(0, i);
                if (symbolMap.containsKey(sym)) {
                    Symbol symbol = symbolMap.get(sym);
                    tokens.add(new Token(symbol,location));

                    rest = rest.substring(i);
                    continue M;
                }
            }

            // Cannot find Match
            System.err.println("!!!! Invalid symbol '" + str.toString() + "' @ " + location);
        }


//        // Parse the token.
//        State state = operatorStart;
//        boolean accept = false;
//        L:for (;;) {
//            int s = sym(next);
//
//            if (s == -1) break;
//
//            switch (state.stateInfo[s]) {
//                case REJECT:
//                    // Already reached accepting.
//                    if (accept) {
//                        break L;
//                    }
//                    // Otherwise: Illegal Symbol.
//                    System.err.println("!!!! Invalid symbol @ " + location);
//                    return null;
//                case PARTIAL:
//                    str.appendCodePoint(next);
//                    state = state.stateNext[s];
//                    if (state == null) {
//                        // This Should be unreachable.
//                        throw new RuntimeException("Invalid State Machine");
//                    }
//                    break;
//                case ACCEPT:
//                    str.appendCodePoint(next);
//                    state = state.stateNext[s];
//                    accept = true;
//                    if (state == null) {
//                        // End of state machine.
//                        nextchar();
//                        break L;
//                    }
//                    break;
//            }
//
//            nextchar();
//        }
//
//        // Get Symbol and Return.
//        if (accept) {
//            Symbol symbol = operatorMap.get(str.toString());
//            return new Token(symbol, location);
//        }

    }

    // String or Character Literal //
    private Token strToken () throws IOException {
        // Symbol.
        Symbol symbol;
        // Location.
        Location location = currentLocation();
        // Literal String, to be parsed as the corresponding value type.
        StringBuilder str = new StringBuilder();

        // Termination character.
        int term = next;

        // Assign Symbol.
        if (next == '"') {
            symbol = strSymbol;
        } else if (next == '\'') {
            symbol = chrSymbol;
        } else {
            // This Should never happen.
            return null;
        }

        // Read string value.
        for (;;) {
            nextchar();

            // End of String.
            if (next == term) {
                nextchar();
                break;
            }

            // End of Line.
            if (next == '\n') {
                System.err.println("!!!! Incomplete String Literal");
                return null;
            }

            // End of File.
            if (next == -1) {
                System.err.println("!!!! Incomplete String Literal");
                return null;
            }

            // Escape Sequence.
            if (next == '\\') {
                nextchar();
                int x = readESC();
                if (x == -1) {
                    System.err.println("!!!! Invalid escape sequence");
                    continue;
                }
                str.appendCodePoint(x);
                continue;
            }

            // Regular Character.
            str.appendCodePoint(next);
        }

        return new Token(symbol, str.toString(), location);
    }


    /* Character Type Predicates */

    // White Space. //
    private static boolean ws (int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    // Text (Identifier/Keyword). //
    private static boolean text (int ch) {
        return ('A' <= ch && ch <= 'Z') ||
               ('a' <= ch && ch <= 'z') ||
                (ch == '_');
    }

    // Symbol. //
    private static int sym (int ch) {
        return operatorSymbols.indexOf(ch);
    }

    // Binary Digit. //
    //  * Returns value of digit, or -1 if not a binary digit.
    private static int binum (int ch) {
        if ('0' <= ch && ch <= '1')
            return ch - '0';

        return -1;
    }

    // Decimal digit. //
    //  * Returns value of digit, or -1 if not a binary number.
    private static int num (int ch) {
        if ('0' <= ch && ch <= '9')
            return ch - '0';

        return -1;
    }

    // Octal digit. //
    // * Returns value of digit, or -1 if not a binary number.
    private static int octnum (int ch) {
        if ('0' <= ch && ch <= '7')
            return ch - '0';

        return -1;
    }

    // Hexadecimal Digit. //
    //   * Returns value of digit, or -1 if not a hexadecimal digit.
    private static int hexnum (int ch) {
        if ('0' <= ch && ch <= '9')
            return ch - '0';

        if ('A' <= ch && ch <= 'F')
            return 10 + ch - 'A';

        if ('a' <= ch && ch <= 'f')
            return 10 + ch - 'a';

        return -1;
    }


    /* Parse Numbers and Escape Sequences */

    private int readESC () throws IOException  {
        // Return character.
        int r;

        // Select character.
        switch (next) {

            case '0':   //                        //
                r = 0;  // This case is temporary //
                break;  //                        //

            case 'a':
                r = 0x07;
                break;
            case 'b':
                r = 0x08;
                break;
            case 'e':
                r = 0x1B;
                break;
            case 'f':
                r = 0x0C;
                break;
            case 'n':
                r = 0x0A;
                break;
            case 'r':
                r = 0x0D;
                break;
            case 't':
                r = 0x09;
                break;
            case 'v':
                r = 0x0B;
                break;
            case '\\':
                r = 0x5C;
                break;
            case '\'':
                r = 0x27;
                break;
            case '"':
                r = 0x22;
                break;
            default:
                return -1;
        }

        // Advance and Return.
        nextchar();
        return r;
    }


    /* Initializer */

    private void initialize () {

        // List of Symbols in Grammar
        List <Symbol> symbols = grammar.symbols();

        // Iterate over each symbol
        // Add symbol based on its type.
        for (Symbol symbol : symbols) {
            switch (symbol.type()) {
                case ID:
                    idSymbol = symbol;
                    break;
                case KEYWORD:
                    keywordMap.put(symbol.text(), symbol);
                    break;
                case OPERATOR:
                    operatorMap.put(symbol.text(), symbol);
                    break;
                case EOF:
                    eofSymbol = symbol;
                    break;
                case INT:
                    intSymbol = symbol;
                    break;
                case BYTE_INT :
                    byteSymbol = symbol;
                    break;
                case SHORT_INT:
                    shortSymbol = symbol;
                    break;
                case LONG_INT:
                    longSymbol = symbol;
                    break;
                case REAL:
                    floatSymbol = symbol;
                    break;
                case DOUBLE_REAL:
                    doubleSymbol = symbol;
                    break;
                case STRING:
                    strSymbol = symbol;
                    break;
                case CHARACTER:
                    chrSymbol = symbol;
                    break;
            }
        }


//        // Read Parameters.
//        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
//
//            String ln;
//            while ((ln = in.readLine()) != null) {
//                // Prepare String.
//                ln = ln.trim();
//
//                // Check if Comment or Empty.
//                if (ln.startsWith("##") || ln.isEmpty()) {
//                    continue;
//                }
//
//                // Split String.
//                String[] opt = ln.trim().split("  *");
//                if (opt.length != 2) {
//                    //Invalid Option
//                    System.err.println("!!!! MAJOR ERROR: INVALID SCANNER FILE !!!!");
//                    throw new RuntimeException("Scanner Error");
//                }
//
//                switch (opt[0]) {
//                    case "@id":
//                        idSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@int":
//                        intSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@short":
//                        shortSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@long":
//                        longSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@float":
//                        floatSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@double":
//                        doubleSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@str":
//                        strSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@chr":
//                        chrSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@eof":
//                        eofSymbol = symbolMap.get(opt[1]);
//                        break;
//                    case "@key":
//                        keywordMap.put(opt[1], symbolMap.get(opt[1]));
//                        break;
//                    case "@op":
//                        operatorMap.put(opt[1], symbolMap.get(opt[1]));
//                        break;
//                    default:
//                        System.err.println("!!!! MAJOR ERROR: INVALID SCANNER FILE !!!!");
//                        throw new RuntimeException("Scanner Error");
//                }
//            }
//        } catch (IOException  x) {
//            System.err.println("!!!! MAJOR ERROR: SCANNER FILE COULD NOT BE READ !!!!");
//            throw new RuntimeException("Scanner Error");
//        }
//
//        // Generate Operator Scanning Parameters //
//
//        // Initialize.
//        operatorStart = new State();
//
//        // Populate.
//        for (String op : operatorMap.keySet()) {
//            int i;
//            State state = operatorStart;
//
//            // Partial.
//            for (i = 0; i < op.length() - 1; ++i) {
//                int ch = op.charAt(i);
//                int x = sym(ch);
//                if (state.stateInfo[x] != ACCEPT) {
//                    state.stateInfo[x] = PARTIAL;
//                }
//                if (state.stateNext[x] == null) {
//                    state.stateNext[x] = new State();
//                }
//                state = state.stateNext[x];
//            }
//
//            // Accept.
//            state.stateInfo[sym(op.charAt(i))] = ACCEPT;
//        }
    }

}
