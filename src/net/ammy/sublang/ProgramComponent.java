package net.ammy.sublang;

import net.ammy.lang.AccessLevel;

/**
 *  Sublang Component representing program structure.
 *      - Package definitions, includes and program definititons.
 */
public abstract class ProgramComponent {

    // No outside instancing.
    private ProgramComponent () {}

    // Visitor Accept.
    public abstract void accept (ProgramVisitor v);


    /**
     *  Global Package Definition.
     *      - Defines what packages all further definitions will be in.
     *      - Must be first definition; May not appear more than once; May not appear in nested package definitions.
     */
    public static class GlobalPackageDefinition extends ProgramComponent {
        public final String pathname;

        // ### Constructor
        public GlobalPackageDefinition (String _pathname) {
            pathname = _pathname;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     *  Nested Package Definition.
     *      - Defines a set of definitions that will be nested in a sub-package.
     */
    public static class NestedPackageDefinition extends ProgramComponent {
        public final String pathname;
        public final ProgramComponent[] definitions;

        // ### Constructor
        public NestedPackageDefinition (String _pathname, ProgramComponent[] _definitions){
            pathname = _pathname;
            definitions = _definitions;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     *  Import statements.
     *      - Import Packages or Definitions from within a package.
     */
    public static class IncludeDefinition extends ProgramComponent {
        public String pathname;
        public String[] symbols;

        // ### Constructor
        public IncludeDefinition (String _pathname, String[] _symbols) {
            pathname = _pathname;
            symbols = _symbols;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class HeaderDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent type;

        // ### Constructor
        public HeaderDefinition (AccessLevel _accesslevel, String _name, TypeComponent _type) {
            accesslevel = _accesslevel;
            name = _name;
            type = _type;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConstDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent type;
        public ExpressionComponent expr;

        // ### Constructor
        public ConstDefinition (AccessLevel _accesslevel, String _name, TypeComponent _type, ExpressionComponent _expr) {
            accesslevel = _accesslevel;
            name = _name;
            type = _type;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ValueDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent type;
        public ExpressionComponent expr;

        // ### Constructor
        public ValueDefinition (AccessLevel _accesslevel, String _name, TypeComponent _type, ExpressionComponent _expr) {
            accesslevel = _accesslevel;
            name = _name;
            type = _type;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class FuncDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent returntype;
        public PatternComponent[] parameters;
        public ExpressionComponent expr;

        // ### Constructor
        public FuncDefinition (AccessLevel _accesslevel, String _name, TypeComponent _returntype, PatternComponent[] _parameters, ExpressionComponent _expr) {
            accesslevel = _accesslevel;
            name = _name;
            returntype = _returntype;
            parameters = _parameters;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ProcDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent returntype;
        public PatternComponent[] parameters;
        public StatementComponent body;

        // ### Constructor
        public ProcDefinition (AccessLevel _accesslevel, String _name, TypeComponent _returntype, PatternComponent[] _parameters, StatementComponent _body) {
            accesslevel = _accesslevel;
            name = _name;
            returntype = _returntype;
            parameters = _parameters;
            body = _body;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TypenameDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public TypeComponent type;

        // ### Constructor
        public TypenameDefinition (AccessLevel _accesslevel, String _name, TypeComponent _type) {
            accesslevel = _accesslevel;
            name = _name;
            type = _type;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DatatypeDefinition extends ProgramComponent {
        public AccessLevel accesslevel;
        public String name;
        public String[] typevars;
        public DataConstructor[] constructors;

        // ### Constructor
        public DatatypeDefinition (AccessLevel _accesslevel, String _name, String[] _typevars, DataConstructor[] _constructors) {
            accesslevel = _accesslevel;
            name = _name;
            typevars = _typevars;
            constructors = _constructors;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ProgramVisitor v) {
            v.visit(this);
        }
        ///##
    }


}
