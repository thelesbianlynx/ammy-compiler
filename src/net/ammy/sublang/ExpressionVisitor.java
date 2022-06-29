package net.ammy.sublang;

import net.ammy.sublang.ExpressionComponent.*;

public interface ExpressionVisitor {

    public void visit (ValueExpression e);
    public void visit (LoadExpression e);

    public void visit (FieldAccessExpression e);
    public void visit (PropertyAccessExpression e);
    public void visit (TypeSpecializationExpression e);

    public void visit (TupleExpression e);

    public void visit (ListExpression e);
    public void visit (GeneratorExpression e);

    public void visit (NewExpression e);
    public void visit (NewArrayExpression e);

    public void visit (ApplicationExpression e);

    public void visit (NegateExpression e);
    public void visit (NotExpression e);
    public void visit (ComplementExpression e);
    public void visit (LengthExpression e);
    public void visit (CopyExpression e);
    public void visit (DerefExpression e);

    public void visit (AddExpression e);
    public void visit (SubExpression e);
    public void visit (MultExpression e);
    public void visit (DivExpression e);
    public void visit (ModExpression e);
    public void visit (AppendExpression e);
    public void visit (TimesExpression e);
    public void visit (DSubExpression e);
    public void visit (DDivExpression e);
    public void visit (EQExpression e);
    public void visit (NotEQExpression e);
    public void visit (LessExpression e);
    public void visit (LessEQExpression e);
    public void visit (GreaterExpression e);
    public void visit (GreaterEQExpression e);
    public void visit (EqualsExpression e);
    public void visit (AndExpression e);
    public void visit (OrExpression e);
    public void visit (BitwiseAndExpression e);
    public void visit (BitwiseOrExpression e);
    public void visit (BitwiseXorExpression e);
    public void visit (LeftShiftExpression e);
    public void visit (RightShiftExpression e);
    public void visit (UnsignedRightShiftExpression e);

    public void visit (ConditionalExpression e);
    public void visit (SelectExpression e);

    public void visit (LambdaExpression e);
    public void visit (NamedLambdaExpression e);

    public void visit (LetExpression e);
    public void visit (NamedLetExpression e);



}
