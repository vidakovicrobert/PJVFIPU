// Konkretna implementacija kalkulatora
class BasicCalculator<T extends Number> extends Calculator<T> {
    @Override
    T add(T a, T b) { // Omotavanje
        if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue()); // Downcasting
        } else if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + b.intValue()); // Downcasting
        }
        return null;
    }

    @Override
    T subtract(T a, T b) { // Omotavanje
        if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() - b.doubleValue()); // Downcasting
        } else if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() - b.intValue()); // Downcasting
        }
        return null;
    }

    @Override
    T multiply(T a, T b) { // Omotavanje
        if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() * b.doubleValue()); // Downcasting
        } else if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() * b.intValue()); // Downcasting
        }
        return null;
    }

    @Override
    T divide(T a, T b) { // Omotavanje
        if (b.doubleValue() != 0) {
            if (a instanceof Double) {
                return (T) Double.valueOf(a.doubleValue() / b.doubleValue()); // Downcasting
            } else if (a instanceof Integer) {
                return (T) Integer.valueOf(a.intValue() / b.intValue()); // Downcasting
            }
        } else {
            throw new ArithmeticException("Division by zero");
        }
        return null;
    }
}