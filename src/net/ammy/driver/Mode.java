package net.ammy.driver;

/**
 * Compiler Mode.
 *  - Determines what phase(s) of the compiler to run.
 *
 */
public enum Mode {

    /**
     * The Default Compiler Mode.
     * Compiles all input files into a single output .c file.
     * Run when no mode selector is present.
     */
    DEFAULT,

    /**
     * Runs Precompiler Stage (-A).
     * Runs the precompiler on each input file and ouputs the intermediate file.
     */
    PRECOMPILE,

    /**
     * Generates Global Symbol Table (-T).
     * Generates a global symbol table from a group of intermediate files.
     */
    TABLE_GEN,

    /**
     * Runs Compile Stage (-C).
     * Compiles all input files into .c source files.
     */
    COMPILE,

    /**
     * Prints general information about this compiler (-P).
     */
    PRINT_INFO,

    /**
     * Prints version information about this compiler (-V).
     */
    PRINT_VERSION;

}
