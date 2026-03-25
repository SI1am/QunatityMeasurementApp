package utils;


import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private final Queue<Connection> availableConnections = new LinkedList<>();
    private final int maxPoolSize;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public ConnectionPool() {
        this.maxPoolSize = ApplicationConfig.getIntProperty("db.pool.size");
        this.dbUrl = ApplicationConfig.getProperty("db.url");
        this.dbUser = ApplicationConfig.getProperty("db.user");
        this.dbPassword = ApplicationConfig.getProperty("db.password");

        initializePool();
    }

    private void initializePool() {
        try {
            for (int i = 0; i < maxPoolSize; i++) {
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                availableConnections.offer(connection);
            }
            logger.info("Connection Pool initialized with " + maxPoolSize + " connections.");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public synchronized Connection getConnection() {
        if (availableConnections.isEmpty()) {
            throw new RuntimeException("No available database connections in the pool.");
        }
        return availableConnections.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            availableConnections.offer(connection);
        }
    }

    public synchronized String getPoolStatistics() {
        return "Available Connections: " + availableConnections.size() + "/" + maxPoolSize;
    }

    public synchronized void closeAllConnections() {
        while (!availableConnections.isEmpty()) {
            try {
                Connection connection = availableConnections.poll();
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.warning("Error closing connection: " + e.getMessage());
            }
        }
        logger.info("All database connections closed.");
    }
}