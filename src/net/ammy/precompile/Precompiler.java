package net.ammy.precompile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import net.ammy.precompile.GrammarUtils.GrammarInfo;
import net.ammy.precompile.ParserUtils.ParseMachine;

/**
 * Precompiler Main class
 */
public class Precompiler {


    // Test Main Method.
    public static void main (String[] args) throws IOException {
        // Language File
        File langfile = new File("lang/lili-1.lang");

        // Grammar
        Grammar grammar = new Grammar(langfile);
        System.out.println(grammar.productionRules().get(41));

        // Grammar Info
        GrammarInfo info = GrammarUtils.getGrammarInfo(grammar);

        // Sanner
        Scanner scanner = new Scanner(grammar);

        // Scan.
        List <Token> tokens = scanner.scan(new File("test/lili-experiment-test.ls"));//new File("lili-test.ls"));

        //System.out.println(tokens);

        // Parse Machine
        ParseMachine parseMachine;

        // Load/Generate Parse Machine.
        File parseFile = new File("parsercache/" + langfile.getName() + ".parsercache");
        if (langfile.lastModified() > parseFile.lastModified()) {
            // Parser cache for this language file is invalid.
            // Parser needs to be generated.
            System.out.println("Generating parser...");
            long t = System.currentTimeMillis();
            parseMachine = ParserUtils.getParser(info);
            long dt = System.currentTimeMillis();
            System.out.println("Done (took " + (dt-t)/60000f + "m)");
            ParserUtils.writeParser(parseMachine, parseFile);
        } else {
            // Parser cache is still valid.
            long t = System.currentTimeMillis();
            parseMachine = ParserUtils.readParser(parseFile, grammar);
            long dt = System.currentTimeMillis();
            System.out.println("Read in " + (dt-t) + "ms");
        }

        // Parser.
        Parser parser = new Parser(grammar, parseMachine);

        Token root = parser.parse(tokens);

        // Print.
        //System.out.println(root);
    }


}
