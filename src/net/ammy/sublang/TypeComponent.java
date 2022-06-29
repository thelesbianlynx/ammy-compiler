package net.ammy.sublang;

public abstract class TypeComponent {

    // No outside instancing.
    private TypeComponent () {}

    // Visitor Accept.
    public abstract void accept (TypeVisitor v);

    /**
     */
    public static class IntType extends TypeComponent {
        // ### Constructor
        public IntType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class UIntType extends TypeComponent {
        // ### Constructor
        public UIntType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class CharType extends TypeComponent {
        // ### Constructor
        public CharType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class UCharType extends TypeComponent {
        // ### Constructor
        public UCharType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ShortType extends TypeComponent {
        // ### Constructor
        public ShortType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class UShortType extends TypeComponent {
        // ### Constructor
        public UShortType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class LongType extends TypeComponent {
        // ### Constructor
        public LongType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ULongType extends TypeComponent {
        // ### Constructor
        public ULongType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class FloatType extends TypeComponent {
        // ### Constructor
        public FloatType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class DoubleType extends TypeComponent {
        // ### Constructor
        public DoubleType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class BooleanType extends TypeComponent {
        // ### Constructor
        public BooleanType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class VoidType extends TypeComponent {
        // ### Constructor
        public VoidType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class XPlaceholderType extends TypeComponent {
        // ### Constructor
        public XPlaceholderType () {}
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ListType extends TypeComponent {
        public final TypeComponent subtype;

        // ### Constructor
        public ListType (TypeComponent _subtype) {
            subtype = _subtype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ArrayType extends TypeComponent {
        public final TypeComponent subtype;

        // ### Constructor
        public ArrayType (TypeComponent _subtype) {
            subtype = _subtype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConstArrayType extends TypeComponent {
        public final TypeComponent subtype;

        // ### Constructor
        public ConstArrayType (TypeComponent _subtype) {
            subtype = _subtype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class PointerType extends TypeComponent {
        public final TypeComponent subtype;

        // ### Constructor
        public PointerType (TypeComponent _subtype) {
            subtype = _subtype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConstPointerType extends TypeComponent {
        public final TypeComponent subtype;

        // ### Constructor
        public ConstPointerType (TypeComponent _subtype) {
            subtype = _subtype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class NamedType extends TypeComponent {
        public final String typename;

        // ### Constructor
        public NamedType (String _typename) {
            typename = _typename;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class ConstructedType extends TypeComponent {
        public final TypeComponent typeconstructor;
        public final TypeComponent typeargument;

        // ### Constructor
        public ConstructedType (TypeComponent _typeconstructor, TypeComponent _typeargument) {
            typeconstructor = _typeconstructor;
            typeargument = _typeargument;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class TupleType extends TypeComponent {
        public final TypeComponent[] subtypes;

        // ### Constructor
        public TupleType (TypeComponent[] _subtypes) {
            subtypes = _subtypes;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class StructureType extends TypeComponent {
        public final Declaration[] fields;

        // ### Constructor
        public StructureType (Declaration[] _fields) {
            fields = _fields;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class FunctionType extends TypeComponent {
        public final TypeComponent parametertype;
        public final TypeComponent returntype;

        // ### Constructor
        public FunctionType (TypeComponent _parametertype, TypeComponent _returntype) {
            parametertype = _parametertype;
            returntype = _returntype;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }

    /**
     */
    public static class PolyType extends TypeComponent {
        public final String[] typevars;
        public final TypeComponent type;

        // ### Constructor
        public PolyType (String[] _typevars, TypeComponent _type) {
            typevars = _typevars;
            type = _type;
        }
        ///##
        // ### Accept
        @Override
        public void accept (TypeVisitor v) {
            v.visit(this);
        }
        ///##
    }
}
