package QuantityMeasurementApp.com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeet_SameValue() {
        QuantityMeasurementApp.Feet value1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet value2 = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(value1.equals(value2));
    }

    @Test
    void testFeet_DifferentValue() {
        QuantityMeasurementApp.Feet value1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet value2 = new QuantityMeasurementApp.Feet(2.0);
        assertFalse(value1.equals(value2));
    }

    @Test
    void testFeet_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(value.equals(null));
    }

    @Test
    void testFeet_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(value.equals("1.0"));
    }

    @Test
    void testFeet_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(value.equals(value));
    }

    @Test
    void testInches_SameValue() {
        QuantityMeasurementApp.Inches value1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches value2 = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(value1.equals(value2));
    }

    @Test
    void testInches_DifferentValue() {
        QuantityMeasurementApp.Inches value1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches value2 = new QuantityMeasurementApp.Inches(2.0);
        assertFalse(value1.equals(value2));
    }

    @Test
    void testInches_NullComparison() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(value.equals(null));
    }

    @Test
    void testInches_NonNumericInput() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(value.equals("1.0"));
    }

    @Test
    void testInches_SameReference() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(value.equals(value));
    }
}
