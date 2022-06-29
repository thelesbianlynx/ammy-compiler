package net.ammy.driver;

/**
 * Driver Main Class.
 *
 */
public class Driver {


    /**
     * Main Function.
     * @param args Command line arguments.
     */
    public static void main (String[] args) {

        // Check: args is empty.
        if (args.length == 0) {
            System.err.println("Usage: lynx [ MODE ] [ OPTIONS ] FILES ...");
            System.exit(-1);
        }


    }
}
