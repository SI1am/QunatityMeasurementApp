package repository;

import entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache;

    private QuantityMeasurementCacheRepository() {
        cache = new ArrayList<>();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {

        if (instance == null)
            instance = new QuantityMeasurementCacheRepository();

        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        cache.add(entity);
    }

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return cache;
    }
}