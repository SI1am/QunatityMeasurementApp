package QuantityMeasurementApp.com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {


    @Test
    public void testEquality_YardToYard_SameValue() {
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(yard1, yard2);
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(2.0, Length.LengthUnit.YARDS);
        assertNotEquals(yard1, yard2);
    }


    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(feet, yard);
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(2.0, Length.LengthUnit.FEET);
        assertNotEquals(yard, feet);
    }


    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertEquals(yard, inches);
    }

    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(inches, yard);
    }


    @Test
    public void testEquality_centimetersToInches_EquivalentValue() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, Length.LengthUnit.INCHES);
        assertEquals(cm, inches);
    }

    @Test
    public void testEquality_centimetersToFeet_NonEquivalentValue() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertNotEquals(cm, feet);
    }


    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }


    @Test
    public void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    public void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }


    @Test
    public void testEquality_YardSameReference() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    @Test
    public void testEquality_CentimetersSameReference() {
        Length cm = new Length(2.0, Length.LengthUnit.CENTIMETERS);
        assertEquals(cm, cm);
    }


    @Test
    public void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertNotEquals(yard, null);
    }

    @Test
    public void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertNotEquals(cm, null);
    }


    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);
        Length feet = new Length(6.0, Length.LengthUnit.FEET);
        Length inches = new Length(72.0, Length.LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }
    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        assertEquals(12.0, result, 1e-6);
    }

    @Test
    public void testConversion_YardsToFeet() {
        double result = Length.convert(3.0,Length.LengthUnit.YARDS,
                Length.LengthUnit.FEET);

        assertEquals(9.0, result, 1e-6);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        double result = Length.convert(2.54,Length.LengthUnit.CENTIMETERS,Length.LengthUnit.INCHES);

        assertEquals(1.0, result, 1e-6);
    }
    
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SameUnit_InchesPlusInches() {
        Length l1 = new Length(6.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(6.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_YardsPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullOperand() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, Length.LengthUnit.FEET);
        Length l2 = new Length(1e6, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2e6, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SmallValues() {
        Length l1 = new Length(0.001, Length.LengthUnit.FEET);
        Length l2 = new Length(0.002, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(0.003, Length.LengthUnit.FEET), result);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(new Length(0.6666666666666666, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {

        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

        assertEquals(new Length(5.08, Length.LengthUnit.CENTIMETERS), result);
    }

    @Test
    public void testAddition_Commutativity_WithTargetUnit() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = l1.add(l2, Length.LengthUnit.YARDS);
        Length result2 = l2.add(l1, Length.LengthUnit.YARDS);

        assertEquals(result1, result2);
    }

    @Test
    public void testAddition_WithZero_TargetUnit() {

        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(new Length(1.6666666666666667, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_NegativeValues_TargetUnit() {

        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(new Length(36.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_NullTargetUnit() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(l2, null);
        });
    }

    @Test
    public void testAddition_LargeValues_TargetUnit() {

        Length l1 = new Length(1000.0, Length.LengthUnit.FEET);
        Length l2 = new Length(500.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(new Length(18000.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_SmallValues_TargetUnit() {

        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(new Length(0.6666666666666666, Length.LengthUnit.YARDS), result);
    }

}