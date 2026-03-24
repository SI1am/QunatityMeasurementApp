package units;

public interface SupportsArithmetic {

    default boolean supportsAddition() {
        return true;
    }

    default boolean supportsSubtraction() {
        return true;
    }

    default boolean supportsDivision() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(operation + " not supported");
    }
}