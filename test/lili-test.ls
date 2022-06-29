package x.y.z;


datatype TestType {
    > A;
    > B : int;
    > C {
        var x[float];
        var y[float];
        var z[float];
    }

    var name[string];
    var id[unsigned long];

    cons A () :
        (name = "A")
        (id = 1);

    cons B n :
        (name = "B")
        (id = 2)
        (B n);

    cons C _x[float] _y[float] _z[float] :
        (name = "C")
        (id = 3)
        (x = _x)
        (y = _y)
        (z = _z);


    |[operator this+int: int]
    |fn operator this + x = id + x;
    |fn operator (B n) + x = n + x;

    [appendToName: string -> string]
    fn appendToName s = name:append s;
    fn (B n) appendToName s = (name:append s):append (inttostring n);
}




[makeMyList: () -> int~]
fn makeMyList () = 1 ++ 2 ++ 3 ++ 4 ++ 5;

[empty: {a} a const~ -> boolean]
fn empty (null) = true;
fn empty (cons x xs) = false;

[first: {a} a const~ -> a]
fn first (null) = default;
fn first (cons x xs) = x;

[rest: {a} a const~ -> a const~ ]
fn rest (null) = null;
fn rest (cons x xs) = xs;





|closure(S)
|For each item [A → α ⋅ B β, t] in S,
|  For each production B → γ in G,
|    For each token b in FIRST(βt),
|      Add [B → ⋅ γ, b] to S
|
|States: 4943       (LR(1))
|Actions: 90469
|
|States: 704        (SLR(1))
|Actions: 11055
