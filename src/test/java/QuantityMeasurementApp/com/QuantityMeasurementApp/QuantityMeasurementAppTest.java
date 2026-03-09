package QuantityMeasurementApp.com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
	
	private static final double EPSILON = 0.01;
	
	
	
	
	@Test
	void testLengthUnitEnum_FeetConstant() {
		assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
	}
	
	@Test
	void testLengthUnitEnum_InchesConstant() {
		assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
	}
	
	@Test
	void testLengthUnitEnum_YardsConstant() {
		assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
	}
	
	@Test
	void testLengthUnitEnum_CentimetersConstant() {
		assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
	}
	
	
	
	
	@Test
	void testConvertToBaseUnit_FeetToFeet() {
		assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
	}
	
	@Test
	void testConvertToBaseUnit_InchesToFeet() {
		assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
	}
	
	@Test
	void testConvertToBaseUnit_YardsToFeet() {
		assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
	}
	
	@Test
	void testConvertToBaseUnit_CentimetersToFeet() {
		assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
	}
	
	
	
	@Test
	void testConvertFromBaseUnit_FeetToFeet() {
		assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
	}
	
	@Test
	void testConvertFromBaseUnit_FeetToInches() {
		assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
	}
	
	@Test
	void testConvertFromBaseUnit_FeetToYards() {
		assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
	}
	
	@Test
	void testConvertFromBaseUnit_FeetToCentimeters() {
		assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
	}
	
	
	
	@Test
	void testQuantityLengthRefactored_Equality() {
		
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
		
		assertEquals(q1, q2);
	}
	
	
	
	@Test
	void testQuantityLengthRefactored_ConvertTo() {
		
		QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
		
		QuantityLength result = q.convertTo(LengthUnit.INCHES);
		
		assertEquals(12.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}
	
	
	
	@Test
	void testQuantityLengthRefactored_Add() {
		
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
		
		QuantityLength result = q1.add(q2, LengthUnit.FEET);
		
		assertEquals(2.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}
	
	@Test
	void testQuantityLengthRefactored_AddWithTargetUnit() {
		
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
		
		QuantityLength result = q1.add(q2, LengthUnit.YARDS);
		
		assertEquals(0.666, result.getValue(), 0.01);
		assertEquals(LengthUnit.YARDS, result.getUnit());
	}
	
	
	
	@Test
	void testQuantityLengthRefactored_NullUnit() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityLength(1.0, null);
		});
	}
	
	@Test
	void testQuantityLengthRefactored_InvalidValue() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityLength(Double.NaN, LengthUnit.FEET);
		});
	}
	
	
	
	@Test
	void testRoundTripConversion_RefactoredDesign() {
		
		QuantityLength original = new QuantityLength(5.0, LengthUnit.FEET);
		
		QuantityLength converted = original.convertTo(LengthUnit.INCHES);
		QuantityLength back = converted.convertTo(LengthUnit.FEET);
		
		assertEquals(original.getValue(), back.getValue(), EPSILON);
	}
	
	
	@Test
	void testUnitImmutability() {
		
		LengthUnit unit = LengthUnit.FEET;
		
		assertEquals(1.0, unit.getConversionFactor(), EPSILON);
	}
	
	
	
	@Test
	void testWeightUnitEnum_KilogramConstant() {
		assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), EPSILON);
	}
	
	@Test
	void testWeightUnitEnum_GramConstant() {
		assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), EPSILON);
	}
	
	@Test
	void testWeightUnitEnum_PoundConstant() {
		assertEquals(0.453592, WeightUnit.POUND.getConversionFactor(), EPSILON);
	}
	
	
	
	@Test
	void testConvertToBaseUnit_GramToKilogram() {
		assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), EPSILON);
	}
	
	@Test
	void testConvertToBaseUnit_PoundToKilogram() {
		assertEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0), EPSILON);
	}
	
	@Test
	void testConvertFromBaseUnit_KilogramToGram() {
		assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), EPSILON);
	}
	
	@Test
	void testConvertFromBaseUnit_KilogramToPound() {
		assertEquals(2.20462, WeightUnit.POUND.convertFromBaseUnit(1.0), 0.01);
	}
	
	
	
	@Test
	void testQuantityWeight_Equality_KgToKg() {
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		
		assertEquals(w1, w2);
	}
	
	@Test
	void testQuantityWeight_Equality_KgToGram() {
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
		
		assertEquals(w1, w2);
	}
	
	@Test
	void testQuantityWeight_Equality_GramToPound() {
		
		QuantityWeight w1 = new QuantityWeight(453.592, WeightUnit.GRAM);
		QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.POUND);
		
		assertEquals(w1, w2);
	}
	
	
	
	
	@Test
	void testQuantityWeight_ConvertToGram() {
		
		QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		
		QuantityWeight result = w.convertTo(WeightUnit.GRAM);
		
		assertEquals(1000.0, result.getValue(), EPSILON);
		assertEquals(WeightUnit.GRAM, result.getUnit());
	}
	
	@Test
	void testQuantityWeight_ConvertToPound() {
		
		QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		
		QuantityWeight result = w.convertTo(WeightUnit.POUND);
		
		assertEquals(2.20462, result.getValue(), 0.01);
	}
	
	
	
	@Test
	void testWeightAddition_SameUnit() {
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
		
		QuantityWeight result = w1.add(w2);
		
		assertEquals(3.0, result.getValue(), EPSILON);
	}
	
	@Test
	void testWeightAddition_CrossUnit() {
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
		
		QuantityWeight result = w1.add(w2);
		
		assertEquals(2.0, result.getValue(), EPSILON);
	}
	
	@Test
	void testWeightAddition_TargetUnitGram() {
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
		
		QuantityWeight result = w1.add(w2, WeightUnit.GRAM);
		
		assertEquals(2000.0, result.getValue(), EPSILON);
	}
	
	
	@Test
	void testWeightAddition_WithZero() {
		
		QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);
		
		QuantityWeight result = w1.add(w2);
		
		assertEquals(5.0, result.getValue(), EPSILON);
	}
	
	@Test
	void testWeightAddition_NegativeValues() {
		
		QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(-2000.0, WeightUnit.GRAM);
		
		QuantityWeight result = w1.add(w2);
		
		assertEquals(3.0, result.getValue(), EPSILON);
	}
	
	@Test
	void testQuantityWeight_NullUnit() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityWeight(1.0, null);
		});
	}
	
	@Test
	void testQuantityWeight_InvalidValue() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
		});
	}
	
	
	@Test
	void testWeightVsLength_Incompatible() {
		
		QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
		
		assertFalse(weight.equals(length));
	}
	
}
	