package service;


import dto.*;
import exception.QuantityMeasurementException;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1,QuantityDTO q2) throws QuantityMeasurementException;

    QuantityDTO convert(QuantityDTO source,String targetUnit) throws QuantityMeasurementException;

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2) throws QuantityMeasurementException;

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2)throws QuantityMeasurementException;

    double divide(QuantityDTO q1,QuantityDTO q2) throws QuantityMeasurementException;
}