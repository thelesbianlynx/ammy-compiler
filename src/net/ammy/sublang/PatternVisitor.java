package net.ammy.sublang;

import net.ammy.sublang.PatternComponent.*;

public interface PatternVisitor {

    public void visit (VariablePattern p);
    public void visit (TypePattern p);
    public void visit (ValuePattern p);
    public void visit (NamePattern p);
    public void visit (ConsPattern p);
    public void visit (TuplePattern p);
    public void visit (ListPattern p);
}
