package QuantityMeasurementApp.com.QuantityMeasurementApp;

import controller.QuantityMeasurementController;
import org.junit.jupiter.api.Test;
import repository.QuantityMeasurementCacheRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class LayerArchitectureTest {

    @Test
    void testLayerSeparation_ServiceIndependence() {

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        assertNotNull(service);
    }

    @Test
    void testLayerSeparation_ControllerIndependence() {

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        assertNotNull(controller);
    }

    @Test
    void testLayerDecoupling_ServiceChange() {

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        assertNotNull(controller);
    }
}