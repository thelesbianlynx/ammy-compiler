package net.ammy.sublang;

import net.ammy.sublang.CaseComponent.*;

public interface CaseVisitor {

    public void visit (EQCase c);
    public void visit (MatchCase c);
    public void visit (ElseCase c);
    public void visit (DefaultCase c);
}
