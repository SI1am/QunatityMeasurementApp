package QuantityMeasurementApp.com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {



    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }



    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {

        IMeasurable unit = LengthUnit.FEET;

        assertEquals(1.0, unit.getConversionFactor());
        assertEquals(2.0, unit.convertToBaseUnit(2.0));
        assertEquals(2.0, unit.convertFromBaseUnit(2.0));
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {

        IMeasurable unit = WeightUnit.KILOGRAM;

        assertEquals(1.0, unit.getConversionFactor());
        assertEquals(2.0, unit.convertToBaseUnit(2.0));
        assertEquals(2.0, unit.convertFromBaseUnit(2.0));
    }



    @Test
    void testGenericQuantity_LengthOperations_Equality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testGenericQuantity_LengthOperations_NotEqual() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }



    @Test
    void testGenericQuantity_WeightOperations_Equality() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }



    @Test
    void testGenericQuantity_LengthOperations_Conversion() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue());
    }

    @Test
    void testLengthConversion_InchesToYards() {

        Quantity<LengthUnit> q =
                new Quantity<>(36.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.YARDS);

        assertEquals(1.0, result.getValue());
    }



    @Test
    void testGenericQuantity_WeightOperations_Conversion() {

        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue());
    }


    @Test
    void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
    }



    @Test
    void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                w1.add(w2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue());
    }



    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }


    @Test
    void testEquals_GenericQuantity_Reflexive() {

        Quantity<LengthUnit> q =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testEquals_GenericQuantity_Symmetric() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }



    @Test
    void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }



    @Test
    void testImmutability_GenericQuantity() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                q1.convertTo(LengthUnit.INCHES);

        assertNotSame(q1, q2);
    }

}