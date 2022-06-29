package net.ammy.sublang;

import net.ammy.sublang.StatementComponent.*;

public interface StatementVisitor {

    public void visit (PassStatement s);
    public void visit (LabelStatement s);
    public void visit (BlockStatement s);
    public void visit (LetStatement s);
    public void visit (VarStatement s);
    public void visit (FuncStatement s);
    public void visit (ProcStatement s);
    public void visit (EvalStatement s);
    public void visit (StoreStatement s);
    public void visit (StoreAddressStatement s);
    public void visit (StoreArrayStatement s);
    public void visit (IfStatement s);
    public void visit (ElseStatement s);
    public void visit (SelectStatement s);
    public void visit (ForStatement s);
    public void visit (WhileStatement s);
    public void visit (DoWhileStatement s);
    public void visit (BreakStatement s);
    public void visit (ContinueStatement s);
    public void visit (ReturnStatement s);


}
