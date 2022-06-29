package x.y.z;

get a.b.c;

[pi: double]
const pi = 3.14159;

[half_pi: double]
half_pi = pi/2;

[fact: int -> int]
fact = fn x -> if x == 1 then 1 else x * fact (x-1);

[fib: int -> int]
fn fib (1) = 1;
fn fib (2) = 1;
fn fib x = fib (x-1) - fib (x-2);

list = [(x, y) where x:[1 .. 10], y:[1 .. 10] when x != y];


datatype List a:
    Empty,
    Cons a (List a);

classtype Color {
    [red: float]
    [green: float]
    [blue: float]
}

[Color: float -> float -> float -> Color]
instance Color r g b of Color {
    red = r;
    green = g;
    blue = b;

    [(Color + Color): Color]
    local fn (this+col) = Color (red+col:red) (green + col:green) (blue + col:blue);

    [((Color~) ~ (int~)): int]
    local fn (this ~ i) = 5;
}


fn foo () {
    for i:[0; i < len list; i+1] do {
        let x = list~i;
        print (istr i ++ ":" ++ istr x);
    }

    for x:list do {
        print x;
    }
}

fn blockTest () {
    `main` for x:[0; x<10; x+1] do {
        for y:[0 .. 10] do {
            if x == y then break main;
        }
    }
}

| // 1..10;
| // 0->(len list);
| // 0->(len list) by 4;

| // (x,y) where x://1..10;, y://1..10; when x != y;

| {(x,y) where x:{1..10}, y:{1..10} when x != y}

| [(x,y) where x:[1..10], y:[1..10] when x != y]
