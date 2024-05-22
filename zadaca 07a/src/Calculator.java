// Klasa koja definira osnovne operacije
// Generička verzija klase Calculator
abstract class Calculator<T extends Number> {
    abstract T add(T a, T b);
    abstract T subtract(T a, T b);
    abstract T multiply(T a, T b);
    abstract T divide(T a, T b);
}
