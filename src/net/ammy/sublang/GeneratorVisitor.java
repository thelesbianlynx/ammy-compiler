package net.ammy.sublang;

import net.ammy.sublang.GeneratorComponent.*;

public interface GeneratorVisitor {

    public void visit (ListGenerator g);
    public void visit (GuardedListGenerator g);
    public void visit (IterativeGenerator g);
    public void visit (GuardedIterativeGenerator g);


}
