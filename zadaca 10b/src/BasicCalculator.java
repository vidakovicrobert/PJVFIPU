// Konkretna implementacija kalkulatora
class BasicCalculator<T extends Number> extends Calculator<T> {
    @Override
    T calculate(Operation<T> operation, T a, T b) {
        return operation.operate(a, b);
    }
}