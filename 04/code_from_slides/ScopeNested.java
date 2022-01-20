
// int x = 0;	// Global variable – NOT supported by Java!

public class ScopeNested {
    private static int x; // class scope
    public static void f() {
        int x;          // block scope (Note – now there are three x’s - field, main, and f())
        x = 7;          // f()'s x, not main's or field x. We have no visiblity to main's x.
        System.out.println("f's x = " + x + " and field x = " + ScopeNested.x);
        {
            // int x = 42;  // Unlike C++, Java has NO new local scope within nested { }.
            // So the above statement would result in a "duplicate definition" compiler error
            System.out.println("Before increment, f's x = " + x + " and field x = " + ScopeNested.x);
            ++x;        // increment f()'s x 
            System.out.println("After increment, f's x = " + x + " and field x = " + ScopeNested.x);
        }
        System.out.println("Before increment, field x = " + ScopeNested.x);
        ++ScopeNested.x; // increment field x
        System.out.println("f's x is now = " + x + " and field x = " + ScopeNested.x);
    }
    public static void main(String[] args) {
        int x = 0;  // in main's block scope - there are two x's - field and main
        System.out.println("main's x = " + x + " and field x = " + ScopeNested.x);
        f();
        System.out.println("main's x is now = " + x + " and field x = " + ScopeNested.x);
    }
}
