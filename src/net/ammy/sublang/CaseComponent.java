package net.ammy.sublang;

public abstract class CaseComponent {

    // No outside instancing.
    private CaseComponent () {}

    // Visitor Accept.
    public abstract void accept (CaseVisitor v);

    /**
     */
    public static class EQCase extends CaseComponent {
        public final ExpressionComponent testexpr;
        public final ExpressionComponent expr;

        // ### Constructor
        public EQCase (ExpressionComponent _testexpr, ExpressionComponent _expr) {
            testexpr = _testexpr;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class MatchCase extends CaseComponent {
        public final PatternComponent[] patterns;
        public final ExpressionComponent expr;

        // ### Constructor
        public MatchCase (PatternComponent[] _patterns, ExpressionComponent _expr) {
            patterns = _patterns;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ElseCase extends CaseComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public ElseCase (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DefaultCase extends CaseComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public DefaultCase (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
