package service;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import exception.QuantityMeasurementException;
import repository.IQuantityMeasurementRepository;
import core.Quantity;
import units.*;


public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {
        try {
            Quantity quantity1 = createQuantity(q1);
            Quantity quantity2 = createQuantity(q2);

            boolean result = quantity1.equals(quantity2);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "COMPARE",
                    q1.getValue(),
                    q2.getValue(),
                    0.0,
                    result
            );

            repository.save(entity);

            return result;

        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "COMPARE",
                    q1.getValue(),
                    q2.getValue(),
                    0.0,
                    false,
                    e.getMessage()
            );
            repository.save(errorEntity);

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit) throws QuantityMeasurementException {
        try {
            Quantity quantity = createQuantity(source);

            IMeasurable target = resolveUnit(source.getMeasurementType(), targetUnit);

            Quantity result = quantity.convertTo(target);

            QuantityDTO dto = new QuantityDTO(
                    result.getValue(),
                    targetUnit,
                    source.getMeasurementType()
            );

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    source.getMeasurementType(),
                    "CONVERT",
                    source.getValue(),
                    0.0,
                    result.getValue(),
                    true
            );

            repository.save(entity);

            return dto;

        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                    source.getMeasurementType(),
                    "CONVERT",
                    source.getValue(),
                    0.0,
                    0.0,
                    false,
                    e.getMessage()
            );
            repository.save(errorEntity);

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {
        try {
            Quantity quantity1 = createQuantity(q1);
            Quantity quantity2 = createQuantity(q2);

            Quantity result = quantity1.add(quantity2);

            QuantityDTO dto = new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getMeasurementType()
            );

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "ADD",
                    q1.getValue(),
                    q2.getValue(),
                    result.getValue(),
                    true
            );

            repository.save(entity);

            return dto;

        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "ADD",
                    q1.getValue(),
                    q2.getValue(),
                    0.0,
                    false,
                    e.getMessage()
            );
            repository.save(errorEntity);

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {
        try {
            Quantity quantity1 = createQuantity(q1);
            Quantity quantity2 = createQuantity(q2);

            Quantity result = quantity1.subtract(quantity2);

            QuantityDTO dto = new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getMeasurementType()
            );

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "SUBTRACT",
                    q1.getValue(),
                    q2.getValue(),
                    result.getValue(),
                    true
            );

            repository.save(entity);

            return dto;

        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "SUBTRACT",
                    q1.getValue(),
                    q2.getValue(),
                    0.0,
                    false,
                    e.getMessage()
            );
            repository.save(errorEntity);

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) throws QuantityMeasurementException {
        try {
            Quantity quantity1 = createQuantity(q1);
            Quantity quantity2 = createQuantity(q2);

            double result = quantity1.divide(quantity2);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "DIVIDE",
                    q1.getValue(),
                    q2.getValue(),
                    result,
                    true
            );

            repository.save(entity);

            return result;

        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                    q1.getMeasurementType(),
                    "DIVIDE",
                    q1.getValue(),
                    q2.getValue(),
                    0.0,
                    false,
                    e.getMessage()
            );
            repository.save(errorEntity);

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    private Quantity createQuantity(QuantityDTO dto) throws QuantityMeasurementException {
        IMeasurable unit = resolveUnit(dto.getMeasurementType(), dto.getUnit());
        return new Quantity(dto.getValue(), unit);
    }

    private IMeasurable resolveUnit(String type, String unit) throws QuantityMeasurementException {
        switch (type.toUpperCase()) {
            case "LENGTH":
                return LengthUnit.valueOf(unit.toUpperCase());

            case "WEIGHT":
                return WeightUnit.valueOf(unit.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unit.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit.toUpperCase());

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
    }
}