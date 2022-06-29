package net.ammy.sublang;

public abstract class ListComponent {

    // No outside instancing.
    private ListComponent () {}

    // Visitor Accept.
    public abstract void accept (ListVisitor v);

    /**
     */
    public static class ListElement extends ListComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public ListElement (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ListVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListRange extends ListComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public ListRange (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ListVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListRangeBy extends ListComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;
        public final ExpressionComponent step;


        // ### Constructor
        public ListRangeBy (ExpressionComponent _expr1, ExpressionComponent _expr2, ExpressionComponent _step) {
            expr1 = _expr1;
            expr2 = _expr2;
            step = _step;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ListVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListInclusiveRange extends ListComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public ListInclusiveRange (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ListVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListInclusiveRangeBy extends ListComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;
        public final ExpressionComponent step;


        // ### Constructor
        public ListInclusiveRangeBy (ExpressionComponent _expr1, ExpressionComponent _expr2, ExpressionComponent _step) {
            expr1 = _expr1;
            expr2 = _expr2;
            step = _step;
        }
        ///##
        // ### Accept
        @Override
        public void accept (ListVisitor v) {
            v.visit(this);
        }
        ///##
    }


}
