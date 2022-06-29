package net.ammy.sublang;

public abstract class ExpressionComponent {

    //No outside instancing.
    private ExpressionComponent () {}

    // Visitor Accept.
    public abstract void accept (ExpressionVisitor v);

    /**
     */
    public static class ValueExpression extends ExpressionComponent {
        public final String value;

        // ### Constructor
        public ValueExpression (String _value) {
            value = _value;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LoadExpression extends ExpressionComponent {
        public final String varname;

        // ### Constructor
        public LoadExpression (String _varname) {
            varname = _varname;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class FieldAccessExpression extends ExpressionComponent {
        public final ExpressionComponent expr;
        public final String fieldID;

        // ### Constructor
        public FieldAccessExpression (ExpressionComponent _expr, String _fieldID) {
            expr = _expr;
            fieldID = _fieldID;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class PropertyAccessExpression extends ExpressionComponent {
        public final ExpressionComponent expr;
        public final String propertyname;

        // ### Constructor
        public PropertyAccessExpression (ExpressionComponent _expr, String _propertyname) {
            expr = _expr;
            propertyname = _propertyname;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TypeSpecializationExpression extends ExpressionComponent {
        public final ExpressionComponent expr;
        public final TypeComponent[] types;

        // ### Constructor
        public TypeSpecializationExpression (ExpressionComponent _expr, TypeComponent[] _types) {
            expr = _expr;
            types = _types;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TupleExpression extends ExpressionComponent {
        public final ExpressionComponent[] items;

        // ### Constructor
        public TupleExpression (ExpressionComponent[] _items) {
            items = _items;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListExpression extends ExpressionComponent {
        public final ListComponent[] elements;

        // ### Constructor
        public ListExpression (ListComponent[] _elements) {
            elements = _elements;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class GeneratorExpression extends ExpressionComponent {
        public final ExpressionComponent expr;
        public final GeneratorComponent[] generators;

        // ### Constructor
        public GeneratorExpression (ExpressionComponent _expr, GeneratorComponent[] _generators) {
            expr = _expr;
            generators = _generators;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NewExpression extends ExpressionComponent {
        public final TypeComponent type;
        public final ExpressionComponent expr;

        // ### Constructor
        public NewExpression (TypeComponent _type, ExpressionComponent _expr) {
            expr = _expr;
            type = _type;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NewArrayExpression extends ExpressionComponent {
        public final TypeComponent type;
        public final ExpressionComponent expr;

        // ### Constructor
        public NewArrayExpression (TypeComponent _type, ExpressionComponent _expr) {
            expr = _expr;
            type = _type;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ApplicationExpression extends ExpressionComponent {
        public final ExpressionComponent rator;
        public final ExpressionComponent rand;

        // ### Constructor
        public ApplicationExpression (ExpressionComponent _rator, ExpressionComponent _rand) {
            rator = _rator;
            rand = _rand;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NegateExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public NegateExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NotExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public NotExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ComplementExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public ComplementExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LengthExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public LengthExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class CopyExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public CopyExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DerefExpression extends ExpressionComponent {
        public final ExpressionComponent expr;

        // ### Constructor
        public DerefExpression (ExpressionComponent _expr) {
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class AddExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public AddExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class SubExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public SubExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class MultExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public MultExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DivExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public DivExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ModExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public ModExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class AppendExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public AppendExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TimesExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public TimesExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DSubExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public DSubExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DDivExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public DDivExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class EQExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public EQExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NotEQExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public NotEQExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LessExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public LessExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LessEQExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public LessEQExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class GreaterExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public GreaterExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class GreaterEQExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public GreaterEQExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class EqualsExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public EqualsExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class AndExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public AndExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class OrExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public OrExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BitwiseAndExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public BitwiseAndExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BitwiseOrExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public BitwiseOrExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BitwiseXorExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public BitwiseXorExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LeftShiftExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public LeftShiftExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class RightShiftExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public RightShiftExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class UnsignedRightShiftExpression extends ExpressionComponent {
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public UnsignedRightShiftExpression (ExpressionComponent _expr1, ExpressionComponent _expr2) {
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConditionalExpression extends ExpressionComponent {
        public final ExpressionComponent cond;
        public final ExpressionComponent expr1;
        public final ExpressionComponent expr2;

        // ### Constructor
        public ConditionalExpression (ExpressionComponent _cond, ExpressionComponent _expr1, ExpressionComponent _expr2) {
            cond = _cond;
            expr1 = _expr1;
            expr2 = _expr2;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class SelectExpression extends ExpressionComponent {
        public final ExpressionComponent expr;
        public final CaseComponent[] cases;

        // ### Constructor
        public SelectExpression (ExpressionComponent _expr, CaseComponent[] _cases) {
            expr = _expr;
            cases = _cases;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LambdaExpression extends ExpressionComponent {
        public final TypeComponent returntype;
        public final PatternComponent[] parameters;
        public final ExpressionComponent expr;

        // ### Constructor
        public LambdaExpression (TypeComponent _returntype, PatternComponent[] _parameters, ExpressionComponent _expr) {
            returntype = _returntype;
            parameters = _parameters;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NamedLambdaExpression extends ExpressionComponent {
        public final String name;
        public final TypeComponent returntype;
        public final PatternComponent[] parameters;
        public final ExpressionComponent expr;

        // ### Constructor
        public NamedLambdaExpression (String _name, TypeComponent _returntype, PatternComponent[] _parameters, ExpressionComponent _expr) {
            name = _name;
            returntype = _returntype;
            parameters = _parameters;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LetExpression extends ExpressionComponent {
        public final Binding[] bindings;
        public final ExpressionComponent expr;

        // ### Constructor
        public LetExpression (Binding[] _bindings, ExpressionComponent _expr) {
            bindings = _bindings;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NamedLetExpression extends ExpressionComponent {
        public final String name;
        public final Binding[] bindings;
        public final ExpressionComponent expr;

        // ### Constructor
        public NamedLetExpression (String _name, Binding[] _bindings, ExpressionComponent _expr) {
            name = _name;
            bindings = _bindings;
            expr = _expr;
        }
        ///##
        // ### Accept
        public void accept (ExpressionVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
