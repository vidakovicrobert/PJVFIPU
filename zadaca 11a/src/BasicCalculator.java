// Konkretna implementacija kalkulatora
class BasicCalculator<T extends Number> {
    T calculate(Operation<T> operation, T a, T b) {
        return operation.operate(a, b);
    }
}