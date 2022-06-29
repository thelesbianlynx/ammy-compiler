package net.ammy.sublang;

import net.ammy.sublang.TypeComponent.*;

public interface TypeVisitor {

    public void visit (IntType t);
    public void visit (UIntType t);
    public void visit (CharType t);
    public void visit (UCharType t);
    public void visit (ShortType t);
    public void visit (UShortType t);
    public void visit (LongType t);
    public void visit (ULongType t);
    public void visit (FloatType t);
    public void visit (DoubleType t);
    public void visit (BooleanType t);
    public void visit (VoidType t);
    public void visit (XPlaceholderType t);
    public void visit (ListType t);
    public void visit (ArrayType t);
    public void visit (ConstArrayType t);
    public void visit (PointerType t);
    public void visit (ConstPointerType t);
    public void visit (NamedType t);
    public void visit (TupleType t);
    public void visit (StructureType t);
    public void visit (ConstructedType t);
    public void visit (FunctionType t);
    public void visit (PolyType t);

}
