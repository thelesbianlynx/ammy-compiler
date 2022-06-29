package net.ammy.sublang;

public abstract class StatementComponent {

    // No outside instancing.
    private StatementComponent () {}

    // Visitor Accept.
    public abstract void accept (StatementVisitor v);

    /**
     */
    public static class PassStatement extends StatementComponent {
        // ### Constructor
        public PassStatement () {}
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LabelStatement extends StatementComponent {
        public static String label;

        // ### Constructor
        public LabelStatement (String _label) {
            label = _label;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BlockStatement extends StatementComponent {
        public StatementComponent[] block;

        // ### Constructor
        public BlockStatement (StatementComponent[] _block) {
            block = _block;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LetStatement extends StatementComponent {
        public static Binding[] bindings;

        // ### Constructor
        public LetStatement (Binding[] _bindings) {
            bindings = _bindings;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class VarStatement extends StatementComponent {
        public static Binding[] bindings;

        // ### Constructor
        public VarStatement (Binding[] _bindings) {
            bindings = _bindings;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class FuncStatement extends StatementComponent {
        public String name;
        public TypeComponent returntype;
        public PatternComponent[] parameters;
        public ExpressionComponent expr;

        // ### Constructor
        public FuncStatement (String _name, TypeComponent _returntype, PatternComponent[] _parameters, ExpressionComponent _expr) {
            name = _name;
            returntype = _returntype;
            parameters = _parameters;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ProcStatement extends StatementComponent {
        public String name;
        public TypeComponent returntype;
        public PatternComponent[] parameters;
        public StatementComponent body;

        // ### Constructor
        public ProcStatement (String _name, TypeComponent _returntype, PatternComponent[] _parameters, StatementComponent _body) {
            name = _name;
            returntype = _returntype;
            parameters = _parameters;
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class EvalStatement extends StatementComponent {
        public static ExpressionComponent expr;

        // ### Constructor
        public EvalStatement (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class StoreStatement extends StatementComponent {
        public static ExpressionComponent lvalue;
        public static ExpressionComponent expr;

        // ### Constructor
        public StoreStatement (ExpressionComponent _lvalue, ExpressionComponent _expr) {
            lvalue = _lvalue;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class StoreAddressStatement extends StatementComponent {
        public static ExpressionComponent addr;
        public static ExpressionComponent expr;

        // ### Constructor
        public StoreAddressStatement (ExpressionComponent _addr, ExpressionComponent _expr) {
            addr = _addr;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class StoreArrayStatement extends StatementComponent {
        public static ExpressionComponent array;
        public static ExpressionComponent index;
        public static ExpressionComponent expr;

        // ### Constructor
        public StoreArrayStatement (ExpressionComponent _array, ExpressionComponent _index, ExpressionComponent _expr) {
            array = _array;
            index = _index;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class IfStatement extends StatementComponent {
        public static ExpressionComponent cond;
        public static StatementComponent body;

        // ### Constructor
        public IfStatement (ExpressionComponent _cond, StatementComponent _body) {
            cond = _cond;
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ElseStatement extends StatementComponent {
        public static StatementComponent body;

        // ### Constructor
        public ElseStatement (StatementComponent _body) {
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class SelectStatement extends StatementComponent {
        public static ExpressionComponent expr;
        public static CaseStatementComponent[] cases;

        // ### Constructor
        public SelectStatement (ExpressionComponent _expr, CaseStatementComponent[] _cases) {
            expr = _expr;
            cases = _cases;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ForStatement extends StatementComponent {
        public static GeneratorComponent[] generators;
        public static StatementComponent body;

        // ### Constructor
        public ForStatement (GeneratorComponent[] _generators, StatementComponent _body) {
            generators = _generators;
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class WhileStatement extends StatementComponent {
        public static ExpressionComponent cond;
        public static StatementComponent body;

        // ### Constructor
        public WhileStatement (ExpressionComponent _cond, StatementComponent _body) {
            cond = _cond;
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DoWhileStatement extends StatementComponent {
        public static ExpressionComponent cond;
        public static StatementComponent body;

        // ### Constructor
        public DoWhileStatement (ExpressionComponent _cond, StatementComponent _body) {
            cond = _cond;
            body = _body;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BreakStatement extends StatementComponent {
        public static String label;

        // ### Constructor
        public BreakStatement (String _label) {
            label = _label;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ContinueStatement extends StatementComponent {
        public static String label;

        // ### Constructor
        public ContinueStatement (String _label) {
            label = _label;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ReturnStatement extends StatementComponent {
        public static ExpressionComponent expr;

        // ### Constructor
        public ReturnStatement (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (StatementVisitor v) {
            v.visit(this);
        }
        ///##
    }


}
