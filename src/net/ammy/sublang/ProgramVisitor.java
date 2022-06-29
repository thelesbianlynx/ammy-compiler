package net.ammy.sublang;

import net.ammy.sublang.ProgramComponent.*;

public interface ProgramVisitor {

    public void visit (GlobalPackageDefinition p);
    public void visit (NestedPackageDefinition p);
    public void visit (IncludeDefinition g);
    public void visit (HeaderDefinition d);
    public void visit (ConstDefinition d);
    public void visit (ValueDefinition d);
    public void visit (FuncDefinition d);
    public void visit (ProcDefinition d);
    public void visit (TypenameDefinition d);
    public void visit (DatatypeDefinition d);
}
