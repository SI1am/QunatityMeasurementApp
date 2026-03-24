package entity;

import dto.QuantityDTO;
import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private Object result;
    private String error;

    public QuantityMeasurementEntity(QuantityDTO operand1, String operation, Object result) {
        this.operand1 = operand1;
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(QuantityDTO operand1, QuantityDTO operand2, String operation, Object result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String operation, String error) {
        this.operation = operation;
        this.error = error;
    }

    // -------- GETTERS --------

    public String getOperation() {
        return operation;
    }

    public QuantityDTO getOperand1() {
        return operand1;
    }

    public QuantityDTO getOperand2() {
        return operand2;
    }

    public Object getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {
        if (error != null) {
            return "Operation: " + operation + " ERROR: " + error;
        }

        return "Operation: " + operation +
                " Operand1: " + operand1 +
                " Operand2: " + operand2 +
                " Result: " + result;
    }
}