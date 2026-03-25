package service;
import dto.QuantityDTO;
import exception.QuantityMeasurementException;

import org.junit.jupiter.api.Test;
import repository.QuantityMeasurementCacheRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityServiceTest {

    private final IQuantityMeasurementService service =
            new QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository.getInstance());

    @Test
    void testService_CompareEquality_SameUnit_Success() throws QuantityMeasurementException {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1,"LITRE","VOLUME");

        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() throws QuantityMeasurementException {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1000,"MILLILITRE","VOLUME");

        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {

        QuantityDTO v = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO w = new QuantityDTO(1,"KILOGRAM","WEIGHT");

        assertThrows(RuntimeException.class,() -> service.compare(v,w));
    }

    @Test
    void testService_Convert_Success() throws QuantityMeasurementException {

        QuantityDTO v = new QuantityDTO(1,"LITRE","VOLUME");

        QuantityDTO result = service.convert(v,"MILLILITRE");

        assertEquals(1000.0,result.getValue());
    }

    @Test
    void testService_Add_Success() throws QuantityMeasurementException {

        QuantityDTO q1 = new QuantityDTO(1,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(1000,"MILLILITRE","VOLUME");

        QuantityDTO result = service.add(q1,q2);

        assertEquals(2.0,result.getValue());
    }

    @Test
    void testService_Add_UnsupportedOperation_Error() {

        QuantityDTO t1 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");
        QuantityDTO t2 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");

        assertThrows(RuntimeException.class,() -> service.add(t1,t2));
    }

    @Test
    void testService_Subtract_Success() throws QuantityMeasurementException {

        QuantityDTO q1 = new QuantityDTO(5,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(2,"LITRE","VOLUME");

        QuantityDTO result = service.subtract(q1,q2);

        assertEquals(3.0,result.getValue());
    }

    @Test
    void testService_Divide_Success() throws QuantityMeasurementException {

        QuantityDTO q1 = new QuantityDTO(4,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(2,"LITRE","VOLUME");

        double result = service.divide(q1,q2);

        assertEquals(2.0,result);
    }

    @Test
    void testService_Divide_ByZero_Error() {

        QuantityDTO q1 = new QuantityDTO(4,"LITRE","VOLUME");
        QuantityDTO q2 = new QuantityDTO(0,"LITRE","VOLUME");

        assertThrows(RuntimeException.class,() -> service.divide(q1,q2));
    }
}