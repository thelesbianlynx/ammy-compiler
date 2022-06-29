package net.ammy.driver;

import java.util.Iterator;

/**
 * An Iterator of strings, Used to iterate over each command-line argument.
 *
 */
public class ArgumentList implements Iterator<String>, Iterable<String> {

    // Arguments.
    String[] args;

    // Position in array.
    int position;

    // Constructor.
    public ArgumentList(String[] args) {
        this.args = args;
        this.position = 0;
    }

    // Implements Iteratable.
    // (Just returns this).
    @Override
    public Iterator<String> iterator() {
        return this;
    }

    // Implements Iterator.
    // Returns if the position in the list is within the bound of the list.
    @Override
    public boolean hasNext() {
        return position < args.length;
    }

    // Implements Iterator.
    // Returns the element in the list at position, increments position.
    // If hasNext is false, return null.
    @Override
    public String next() {
        if (position >= args.length)
            return null;

        String arg = args[position];
        ++position;
        return arg;
    }

    // Returns the element in the list at position without incrementing position.
    // If hasNext is false, return null.
    public String peek() {
        if (position >= args.length)
            return null;

        String arg = args[position];
        return arg;
    }



}
