package net.ammy.sublang;

public class Binding {

    public final Declaration[] namelist;
    public final ExpressionComponent init;

    // ### Constructor
    public Binding (Declaration[] _namelist, ExpressionComponent _init) {
        namelist = _namelist;
        init = _init;
    }
    ///##
}
