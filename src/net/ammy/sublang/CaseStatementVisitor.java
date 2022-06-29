package net.ammy.sublang;

import net.ammy.sublang.CaseStatementComponent.*;

public interface CaseStatementVisitor {

    public void visit (EQCaseStatement c);
    public void visit (MatchCaseStatement c);
    public void visit (ElseCaseStatement c);
    public void visit (DefaultCaseStatement c);
}
