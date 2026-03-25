package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class QuantityMeasurementEntity implements Serializable {

    private int id;
    private String measurementType;
    private String operationType;
    private double value1;
    private double value2;
    private double resultValue;
    private boolean resultStatus;
    private String error;
    private Timestamp createdAt;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(String measurementType, String operationType,
                                     double value1, double value2,
                                     double resultValue, boolean resultStatus) {
        this.measurementType = measurementType;
        this.operationType = operationType;
        this.value1 = value1;
        this.value2 = value2;
        this.resultValue = resultValue;
        this.resultStatus = resultStatus;
    }

    public QuantityMeasurementEntity(String measurementType, String operationType,
                                     double value1, double value2,
                                     double resultValue, boolean resultStatus,
                                     String error) {
        this.measurementType = measurementType;
        this.operationType = operationType;
        this.value1 = value1;
        this.value2 = value2;
        this.resultValue = resultValue;
        this.resultStatus = resultStatus;
        this.error = error;
    }

    // ---------------- GETTERS & SETTERS ----------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }


    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }


    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }


    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }


    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }


    public boolean isResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        this.resultStatus = resultStatus;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null && !error.isEmpty();
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", measurementType='" + measurementType + '\'' +
                ", operationType='" + operationType + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", resultValue=" + resultValue +
                ", resultStatus=" + resultStatus +
                ", error='" + error + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}