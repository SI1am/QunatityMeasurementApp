package repository;

import entity.QuantityMeasurementEntity;
import exception.DatabaseException;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = new ConnectionPool();
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurements " +
                "(type, operation, value1, value2, result_value, result_status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, entity.getMeasurementType());
                preparedStatement.setString(2, entity.getOperationType());
                preparedStatement.setDouble(3, entity.getValue1());
                preparedStatement.setDouble(4, entity.getValue2());
                preparedStatement.setDouble(5, entity.getResultValue());
                preparedStatement.setBoolean(6, entity.isResultStatus());

                preparedStatement.executeUpdate();
                logger.info("Measurement saved to database successfully.");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error saving quantity measurement", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    measurements.add(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving all measurements", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

        return measurements;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {

        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements WHERE operation = ?";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, operation);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        measurements.add(mapResultSetToEntity(resultSet));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving measurements by operation", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

        return measurements;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {

        List<QuantityMeasurementEntity> measurements = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurements WHERE type = ?";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, type);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        measurements.add(mapResultSetToEntity(resultSet));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving measurements by type", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

        return measurements;
    }

    @Override
    public int getTotalCount() {

        String sql = "SELECT COUNT(*) FROM quantity_measurements";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error counting quantity measurements", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

        return 0;
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM quantity_measurements";

        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
                logger.info("All quantity measurements deleted from database.");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting all quantity measurements", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public String getPoolStatistics() {
        return connectionPool.getPoolStatistics();
    }

    @Override
    public void releaseResources() {
        connectionPool.closeAllConnections();
    }

    // ✅ FIXED MAPPING
    private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setId(rs.getInt("id"));
        entity.setMeasurementType(rs.getString("type"));
        entity.setOperationType(rs.getString("operation"));
        entity.setValue1(rs.getDouble("value1"));
        entity.setValue2(rs.getDouble("value2"));
        entity.setResultValue(rs.getDouble("result_value"));
        entity.setResultStatus(rs.getBoolean("result_status"));
        entity.setCreatedAt(rs.getTimestamp("created_at"));

        return entity;
    }
}