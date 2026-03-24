package units;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    boolean supportsArithmetic();

    void validateOperationSupport(String operation);
}