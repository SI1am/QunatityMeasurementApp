package controllers;


import controller.QuantityMeasurementController;
import dto.QuantityDTO;
import org.junit.jupiter.api.Test;
import repository.QuantityMeasurementCacheRepository;
import service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityControllerTest {

    private final QuantityMeasurementController controller =
            new QuantityMeasurementController(
                    new QuantityMeasurementServiceImpl(
                            QuantityMeasurementCacheRepository.getInstance()
                    )
            );

    @Test
    void testController_DemonstrateEquality_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1,"LITRE","VOLUME");

        assertDoesNotThrow(() ->
                controller.performComparison(q1,q2));
    }

    @Test
    void testController_DemonstrateConversion_Success() {

        QuantityDTO q = new QuantityDTO(1,"LITRE","VOLUME");

        assertDoesNotThrow(() ->
                controller.performConversion(q,"MILLILITRE"));
    }

    @Test
    void testController_DemonstrateAddition_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1000,"MILLILITRE","VOLUME");

        assertDoesNotThrow(() ->
                controller.performAddition(q1,q2));
    }

    @Test
    void testController_DemonstrateAddition_Error() {

        QuantityDTO t1 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");
        QuantityDTO t2 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");

        assertDoesNotThrow(() ->
                controller.performAddition(t1,t2));
    }

}