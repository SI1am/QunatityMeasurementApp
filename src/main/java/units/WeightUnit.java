package units;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return true;
    }

    @Override
    public void validateOperationSupport(String operation) {

        switch (operation.toUpperCase()) {

            case "ADD":
            case "SUBTRACT":
            case "DIVIDE":
            case "COMPARE":
            case "CONVERT":
                return;

            default:
                throw new UnsupportedOperationException(
                        "Operation not supported: " + operation
                );
        }
    }
}