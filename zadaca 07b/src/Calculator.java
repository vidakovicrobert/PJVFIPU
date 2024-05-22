// Generička verzija klase Calculator
abstract class Calculator<T extends Number> {
    abstract T calculate(Operation<T> operation, T a, T b); // metoda za izvršavanje operacije
}