package net.ammy.sublang;

public abstract class PatternComponent {

    // No outside instancing.
    private PatternComponent () {}

    // Visitor Accept.
    public abstract void accept (PatternVisitor v);

    /**
     */
    public static class VariablePattern extends PatternComponent {
        public final String varname;

        // ### Constructor
        public VariablePattern (String _varname) {
            varname = _varname;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TypePattern extends PatternComponent {
        public final String varname;
        public final TypeComponent type;

        // ### Constructor
        public TypePattern (String _varname, TypeComponent _type) {
            varname = _varname;
            type = _type;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ValuePattern extends PatternComponent {
        public final String value;

        // ### Constructor
        public ValuePattern (String _value) {
            value = _value;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NamePattern extends PatternComponent {
        public final String name;
        public final PatternComponent[] subpatterns;

        // ### Constructor
        public NamePattern (String _name, PatternComponent[] _subpatterns) {
            name = _name;
            subpatterns = _subpatterns;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConsPattern extends PatternComponent {
        public final PatternComponent[] subpatterns;

        // ### Constructor
        public ConsPattern (PatternComponent[] _subpatterns) {
            subpatterns = _subpatterns;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TuplePattern extends PatternComponent {
        public final PatternComponent[] subpatterns;

        // ### Constructor
        public TuplePattern (PatternComponent[] _subpatterns) {
            subpatterns = _subpatterns;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListPattern extends PatternComponent {
        public final PatternComponent[] subpatterns;

        // ### Constructor
        public ListPattern (PatternComponent[] _subpatterns) {
            subpatterns = _subpatterns;
        }
        ///##
        // ### Accept
        @Override
        public void accept (PatternVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
