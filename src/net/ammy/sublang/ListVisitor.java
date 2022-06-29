package net.ammy.sublang;

import net.ammy.sublang.ListComponent.*;

public interface ListVisitor {

    public void visit (ListElement l);
    public void visit (ListRange l);
    public void visit (ListRangeBy l);
    public void visit (ListInclusiveRange l);
    public void visit (ListInclusiveRangeBy l);
}
