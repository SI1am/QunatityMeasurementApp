package QuantityMeasurementApp.com.QuantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final int SCALE = 6;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = round(value);
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return round(unit.convertToBaseUnit(value));
    }

    private static double round(double value) {
        double factor = Math.pow(10, SCALE);
        return Math.round(value * factor) / factor;
    }



    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b ==0)
                throw new ArithmeticException("Cannot divide by zero");
            return a/b;
        });

        private final java.util.function.DoubleBinaryOperator operator;

        ArithmeticOperation(java.util.function.DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }



    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible quantity types");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }



    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

        double base1 = this.toBase();
        double base2 = other.toBase();

        return operation.compute(base1, base2);
    }



    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }


    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }


    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }



    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }



    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}