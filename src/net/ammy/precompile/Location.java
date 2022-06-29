package net.ammy.precompile;

/**
 *
 */
public class Location {

    /* Fields */

    private final String filename;
    private final int line, col;

    /* Constructors */

    public Location (String _file, int _line, int _col) {
        filename = _file;
        line = _line;
        col = _col;
    }

    /* Operators */

    public String filename () {
        return filename;
    }

    public int line () {
        return line;
    }

    public int column () {
        return col;
    }

    /* Overrides */

    @Override
    public String toString () {
        return filename + ":" + line + ":" + col;
    }

}
