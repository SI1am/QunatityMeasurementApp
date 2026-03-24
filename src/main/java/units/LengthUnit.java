package units;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double factor;

    LengthUnit(double factor) {
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