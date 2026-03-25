package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityEntityTest {

    @Test
    void testEntity_ConstructorAndGetters() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "VOLUME",
                        "ADD",
                        1.0,
                        1.0,
                        2.0,
                        true
                );

        assertNotNull(entity);
        assertEquals("VOLUME", entity.getMeasurementType());
        assertEquals("ADD", entity.getOperationType());
        assertEquals(1.0, entity.getValue1());
        assertEquals(1.0, entity.getValue2());
        assertEquals(2.0, entity.getResultValue());
        assertTrue(entity.isResultStatus());
    }

    @Test
    void testEntity_ErrorHandling() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "VOLUME",
                        "ADD",
                        1.0,
                        1.0,
                        0.0,
                        false,
                        "Unsupported operation"
                );

        assertTrue(entity.hasError());
        assertEquals("Unsupported operation", entity.getError());
    }

    @Test
    void testEntity_SettersAndGetters() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setMeasurementType("LENGTH");
        entity.setOperationType("SUBTRACT");
        entity.setValue1(5.0);
        entity.setValue2(2.0);
        entity.setResultValue(3.0);
        entity.setResultStatus(true);

        assertEquals("LENGTH", entity.getMeasurementType());
        assertEquals("SUBTRACT", entity.getOperationType());
        assertEquals(5.0, entity.getValue1());
        assertEquals(2.0, entity.getValue2());
        assertEquals(3.0, entity.getResultValue());
        assertTrue(entity.isResultStatus());
    }

    @Test
    void testEntity_ToString() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "VOLUME",
                        "ADD",
                        1.0,
                        1.0,
                        2.0,
                        true
                );

        String result = entity.toString();

        assertNotNull(result);
        assertTrue(result.contains("ADD"));
        assertTrue(result.contains("VOLUME"));
    }

    @Test
    void testEntity_HasErrorFalse() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "VOLUME",
                        "ADD",
                        1.0,
                        1.0,
                        2.0,
                        true
                );

        assertFalse(entity.hasError());
    }
}