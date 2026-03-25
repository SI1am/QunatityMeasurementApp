package integrationTests;


import dto.QuantityDTO;
import exception.QuantityMeasurementException;

import org.junit.jupiter.api.Test;
import repository.QuantityMeasurementCacheRepository;
import service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityIntegrationTest {

    @Test
    void testIntegration_EndToEnd_LengthAddition() throws QuantityMeasurementException {

        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository.getInstance() );

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");

        QuantityDTO result = service.add(q1,q2);

        assertEquals(2.0,result.getValue());
    }

    @Test
    void testIntegration_EndToEnd_TemperatureUnsupported() {

        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl( QuantityMeasurementCacheRepository.getInstance());

        QuantityDTO t1 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");
        QuantityDTO t2 = new QuantityDTO(10,"CELSIUS","TEMPERATURE");

        assertThrows(RuntimeException.class, () -> service.add(t1,t2));
    }
}