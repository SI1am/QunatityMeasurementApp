package controller;

import dto.QuantityDTO;
import service.IQuantityMeasurementService;
import exception.QuantityMeasurementException;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {

        boolean result = service.compare(q1, q2);

		System.out.println("Comparison Result: " + result);
    }

    public void performConversion(QuantityDTO q, String targetUnit) throws QuantityMeasurementException {

        QuantityDTO result = service.convert(q, targetUnit);

		System.out.println("Converted Result: " + result);
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {

        QuantityDTO result = service.add(q1, q2);

		System.out.println("Addition Result: " + result);
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {

        QuantityDTO result = service.subtract(q1, q2);

		System.out.println("Subtraction Result: " + result);
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {

        double result = service.divide(q1, q2);

		System.out.println("Division Result: " + result);
    }
}