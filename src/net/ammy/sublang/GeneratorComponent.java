package net.ammy.sublang;

public abstract class GeneratorComponent {

    // No outside instancing.
    private GeneratorComponent () {}

    // Visitor Accept.
    public abstract void accept (GeneratorVisitor v);

    /**
     */
    public static class ListGenerator extends GeneratorComponent {
        public final Declaration[] namelist;
        public final ExpressionComponent expr;

        // ### Constructor
        public ListGenerator (Declaration[] _namelist, ExpressionComponent _expr) {
            namelist = _namelist;
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (GeneratorVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class GuardedListGenerator extends GeneratorComponent {
        public final Declaration[] namelist;
        public final ExpressionComponent expr;
        public final ExpressionComponent guard;

        // ### Constructor
        public GuardedListGenerator (Declaration[] _namelist, ExpressionComponent _expr, ExpressionComponent _guard) {
            namelist = _namelist;
            expr = _expr;
            guard = _guard;
        }
        ///##
        // ### Accept
        @Override
        public void accept (GeneratorVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class IterativeGenerator extends GeneratorComponent {
        public final Declaration[] namelist;
        public final ExpressionComponent init;
        public final ExpressionComponent condition;
        public final ExpressionComponent next;

        // ### Constructor
        public IterativeGenerator (Declaration[] _namelist, ExpressionComponent _init, ExpressionComponent _condition, ExpressionComponent _next) {
            namelist = _namelist;
            init = _init;
            condition = _condition;
            next = _next;
        }
        ///##
        // ### Accept
        @Override
        public void accept (GeneratorVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class GuardedIterativeGenerator extends GeneratorComponent {
        public final Declaration[] namelist;
        public final ExpressionComponent init;
        public final ExpressionComponent condition;
        public final ExpressionComponent next;
        public final ExpressionComponent guard;

        // ### Constructor
        public GuardedIterativeGenerator (Declaration[] _namelist, ExpressionComponent _init, ExpressionComponent _condition, ExpressionComponent _next, ExpressionComponent _guard) {
            namelist = _namelist;
            init = _init;
            condition = _condition;
            next = _next;
            guard = _guard;
        }
        ///##
        // ### Accept
        @Override
        public void accept (GeneratorVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
