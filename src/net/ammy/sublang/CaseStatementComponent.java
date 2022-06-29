package net.ammy.sublang;

public abstract class CaseStatementComponent {

    // No outside instancing.
    private CaseStatementComponent () {}

    // Visitor Accept.
    public abstract void accept (CaseStatementVisitor v);

    /**
     */
    public static class EQCaseStatement extends CaseStatementComponent {
        public final ExpressionComponent testexpr;
        public final StatementComponent body;

        // ### Constructor
        public EQCaseStatement (ExpressionComponent _testexpr, StatementComponent _body) {
            testexpr = _testexpr;
            body = _body;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseStatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class MatchCaseStatement extends CaseStatementComponent {
        public final PatternComponent[] patterns;
        public final StatementComponent body;

        // ### Constructor
        public MatchCaseStatement (PatternComponent[] _patterns, StatementComponent _body) {
            patterns = _patterns;
            body = _body;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseStatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ElseCaseStatement extends CaseStatementComponent {
        public final StatementComponent body;

        // ### Constructor
        public ElseCaseStatement (StatementComponent _body) {
            body = _body;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseStatementVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DefaultCaseStatement extends CaseStatementComponent {
        public final StatementComponent body;

        // ### Constructor
        public DefaultCaseStatement (StatementComponent _body) {
            body = _body;
        }
        ///##
        // ### Accept
        @Override
        public void accept (CaseStatementVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
