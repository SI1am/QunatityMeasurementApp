package QuantityMeasurementApp.com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;



    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }


    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }


    @Test
    void testAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_MillilitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    
    @Test
    void testSubtraction_LitreMinusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_LitreMinusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(2.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_NegativeResult() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(-1.0, result.getValue(), EPSILON);
    }


    @Test
    void testDivision_LitreByLitre() {

        double result =
                new Quantity<>(4.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testDivision_ByZero() {

        assertThrows(ArithmeticException.class,
                () -> new Quantity<>(1.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(0.0, VolumeUnit.LITRE)));
    }



    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {

        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    @Test
    void testValidation_CrossCategory_ConsistentAcrossOperations() {

        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> v.add((Quantity) w));
        assertThrows(IllegalArgumentException.class, () -> v.subtract((Quantity) w));
        assertThrows(IllegalArgumentException.class, () -> v.divide((Quantity) w));
    }

    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE));

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, VolumeUnit.LITRE));
    }

    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }


    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {

        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        q1.add(q2);

        assertEquals(1.0, q1.getValue(), EPSILON);
        assertEquals(1.0, q2.getValue(), EPSILON);
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {

        Quantity<VolumeUnit> q1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        q1.subtract(q2);

        assertEquals(2.0, q1.getValue(), EPSILON);
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {

        Quantity<VolumeUnit> q1 = new Quantity<>(4.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        q1.divide(q2);

        assertEquals(4.0, q1.getValue(), EPSILON);
    }


    @Test
    void testImplicitTargetUnit_AddSubtract() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(1500.0, result.getValue(), EPSILON);
    }



    @Test
    void testArithmetic_Chain_Operations() {

        Quantity<VolumeUnit> q1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q3 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q4 = new Quantity<>(1.0, VolumeUnit.LITRE);

        double result =
                q1.add(q2)
                        .subtract(q3)
                        .divide(q4);

        assertEquals(2.5, result, EPSILON);
    }


    @Test
    void testHelper_PrivateVisibility() throws Exception {

        Method method =
                Quantity.class.getDeclaredMethod("performBaseArithmetic",
                        Quantity.class,
                        Class.forName("QuantityMeasurementApp.com.QuantityMeasurementApp.Quantity$ArithmeticOperation"));

        assertTrue(java.lang.reflect.Modifier.isPrivate(method.getModifiers()));
    }

    @Test
    void testValidation_Helper_PrivateVisibility() throws Exception {

        Method method =
                Quantity.class.getDeclaredMethod("validateArithmeticOperands",
                        Quantity.class,
                        Object.class,
                        boolean.class);

        assertTrue(java.lang.reflect.Modifier.isPrivate(method.getModifiers()));
    }




    @Test
    void testEnumConstant_ADD_CorrectlyAdds() throws Exception {

        Class<?> enumClass =
                Class.forName("QuantityMeasurementApp.com.QuantityMeasurementApp.Quantity$ArithmeticOperation");

        Object addEnum = Enum.valueOf((Class<Enum>) enumClass, "ADD");

        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);

        double result = (double) compute.invoke(addEnum, 7.0, 3.0);

        assertEquals(10.0, result, EPSILON);
    }

    @Test
    void testEnumConstant_SUBTRACT_CorrectlySubtracts() throws Exception {

        Class<?> enumClass =
                Class.forName("QuantityMeasurementApp.com.QuantityMeasurementApp.Quantity$ArithmeticOperation");

        Object subEnum = Enum.valueOf((Class<Enum>) enumClass, "SUBTRACT");

        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);

        double result = (double) compute.invoke(subEnum, 7.0, 3.0);

        assertEquals(4.0, result, EPSILON);
    }

    @Test
    void testEnumConstant_DIVIDE_CorrectlyDivides() throws Exception {

        Class<?> enumClass =
                Class.forName("QuantityMeasurementApp.com.QuantityMeasurementApp.Quantity$ArithmeticOperation");

        Object divEnum = Enum.valueOf((Class<Enum>) enumClass, "DIVIDE");

        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);

        double result = (double) compute.invoke(divEnum, 7.0, 2.0);

        assertEquals(3.5, result, EPSILON);
    }

    

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        Quantity<TemperatureUnit> c = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        Quantity<TemperatureUnit> c = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
        assertTrue(f.equals(c));
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> t = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertTrue(t.equals(t));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t1.equals(t2));
    }

    

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        Quantity<TemperatureUnit> t = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(122.0, result.getValue(), EPSILON);

        Quantity<TemperatureUnit> t2 = new Quantity<>(-20.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> r2 = t2.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-4.0, r2.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        Quantity<TemperatureUnit> t = new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(50.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {
        Quantity<TemperatureUnit> original =
                new Quantity<>(35.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> t = new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(20.0, result.getValue());
    }

    @Test
    void testTemperatureConversion_ZeroValue() {
        Quantity<TemperatureUnit> t = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(32.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_NegativeValues() {
        Quantity<TemperatureUnit> t = new Quantity<>(-10.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(14.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_LargeValues() {
        Quantity<TemperatureUnit> t = new Quantity<>(1000.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result = t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(1832.0, result.getValue(), EPSILON);
    }

    // ---------- Unsupported Arithmetic ----------

    @Test
    void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.subtract(t2));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }
    

    @Test
    void testTemperatureVsLengthIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> length = new Quantity<>(100.0, LengthUnit.FEET);

        assertFalse(temp.equals(length));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> weight = new Quantity<>(50.0, WeightUnit.KILOGRAM);

        assertFalse(temp.equals(weight));
    }

    @Test
    void testTemperatureVsVolumeIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<VolumeUnit> volume = new Quantity<>(25.0, VolumeUnit.LITRE);

        assertFalse(temp.equals(volume));
    }

    

    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
        assertNotNull(TemperatureUnit.KELVIN);
    }

    @Test
    void testTemperatureUnit_NameMethod() {
        assertEquals("CELSIUS", TemperatureUnit.CELSIUS.getUnitName());
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(TemperatureUnit.CELSIUS instanceof IMeasurable);
    }
    


    @Test
    void testTemperatureNullUnitValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null));
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {
        Quantity<TemperatureUnit> t = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertFalse(t.equals(null));
    }



    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("addition"));
    }

    

    @Test
    void testTemperatureIntegrationWithGenericQuantity() {
        Quantity<TemperatureUnit> t =
                new Quantity<>(30.0, TemperatureUnit.CELSIUS);

        assertEquals(30.0, t.getValue());
        assertEquals(TemperatureUnit.CELSIUS, t.getUnit());
    }

}