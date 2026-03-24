package QuantityMeasurementApp.com.QuantityMeasurementApp;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityEntityTest {

    @Test
    void testQuantityEntity_SingleOperandConstruction() {

        QuantityDTO input = new QuantityDTO(1, "LITRE", "VOLUME");
        QuantityDTO result = new QuantityDTO(1000, "MILLILITRE", "VOLUME");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(input,"CONVERT",result);

        assertNotNull(entity);
        assertEquals("CONVERT", entity.getOperation());
    }

    @Test
    void testQuantityEntity_BinaryOperandConstruction() {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1000,"MILLILITRE","VOLUME");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1,q2,"ADD","2 LITRE");

        assertNotNull(entity);
        assertEquals("ADD", entity.getOperation());
    }

    @Test
    void testQuantityEntity_ErrorConstruction() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD","Unsupported operation");

        assertTrue(entity.hasError());
    }

    @Test
    void testQuantityEntity_ToString_Success() {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1,"LITRE","VOLUME");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1,q2,"ADD","2 LITRE");

        assertTrue(entity.toString().contains("ADD"));
    }

    @Test
    void testQuantityEntity_ToString_Error() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD","Error occurred");

        assertTrue(entity.toString().contains("Error"));
    }

    @Test
    void testEntity_Immutability() {

        QuantityDTO q = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q,"CONVERT","1000 ML");

        assertNotNull(entity);
    }

    @Test
    void testEntity_OperationType_Tracking() {

        QuantityDTO q = new QuantityDTO(1,"LITRE","VOLUME");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q,"CONVERT","1000 ML");

        assertEquals("CONVERT", entity.getOperation());
    }
}