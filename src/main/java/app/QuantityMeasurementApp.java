package app;

import controller.*;
import dto.*;
import exception.QuantityMeasurementException;
import repository.*;
import service.*;

import java.util.Scanner;

import utils.ApplicationConfig;
public class QuantityMeasurementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private final IQuantityMeasurementRepository repository;
    private final QuantityMeasurementController controller;

    public QuantityMeasurementApp() {
        this.repository = initializeRepository();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        this.controller = new QuantityMeasurementController(service);
    }

    private IQuantityMeasurementRepository initializeRepository() {
        String repoType = ApplicationConfig.getRepositoryType();

        if ("DATABASE".equalsIgnoreCase(repoType)) {
            System.out.println("Using Database Repository...");
            return (IQuantityMeasurementRepository) new QuantityMeasurementDatabaseRepository();
        } else {
            System.out.println("Using Cache Repository...");
            return QuantityMeasurementCacheRepository.getInstance();
        }
    }

    public void run() {
        try {
            while (true) {
                System.out.println("\n====== Quantity Measurement System ======");
                System.out.println("1. Compare Quantities");
                System.out.println("2. Convert Quantity");
                System.out.println("3. Add Quantities");
                System.out.println("4. Subtract Quantities");
                System.out.println("5. Divide Quantities");
                System.out.println("6. Show Total Saved Measurements");
                System.out.println("7. Show Repository Statistics");
                System.out.println("8. Delete All Measurements");
                System.out.println("9. Exit");

                int operation = readInt("Choose operation: ");

                if (operation == 9) {
                    System.out.println("Exiting...");
                    break;
                }

                switch (operation) {
                    case 6 -> showTotalMeasurements();
                    case 7 -> showRepositoryStats();
                    case 8 -> deleteAllMeasurements();
                    case 1, 2, 3, 4, 5 -> processMeasurementOperation(operation);
                    default -> System.out.println("Invalid choice");
                }
            }
        } finally {
            closeResources();
        }
    }

    private void processMeasurementOperation(int operation) {
        try {
            int typeChoice = chooseMeasurementType();
            String measurementType = getMeasurementType(typeChoice);

            double value1 = readDouble("Enter first value: ");
            String unit1 = chooseUnit(typeChoice);

            QuantityDTO q1 = new QuantityDTO(value1, unit1, measurementType);

            if (operation == 2) {
                String targetUnit = chooseUnit(typeChoice);
                controller.performConversion(q1, targetUnit);
                return;
            }

            double value2 = readDouble("Enter second value: ");
            String unit2 = chooseUnit(typeChoice);

            QuantityDTO q2 = new QuantityDTO(value2, unit2, measurementType);

            switch (operation) {
                case 1 -> controller.performComparison(q1, q2);
                case 3 -> controller.performAddition(q1, q2);
                case 4 -> controller.performSubtraction(q1, q2);
                case 5 -> controller.performDivision(q1, q2);
                default -> System.out.println("Invalid operation");
            }

        } catch (QuantityMeasurementException e) {
            System.out.println("Operation failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private void showTotalMeasurements() {
        System.out.println("Total Measurements Saved: " + repository.getTotalCount());
    }

    private void showRepositoryStats() {
        System.out.println("Repository Statistics: " + repository.getPoolStatistics());
    }

    private void deleteAllMeasurements() {
        repository.deleteAll();
        System.out.println("All measurements deleted successfully.");
    }

    private void closeResources() {
        repository.releaseResources();
        System.out.println("Resources released successfully.");
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = new QuantityMeasurementApp();
        app.run();
    }

    private static int chooseMeasurementType() {
        System.out.println("\nSelect Measurement Type:");
        System.out.println("1. Length");
        System.out.println("2. Weight");
        System.out.println("3. Volume");
        System.out.println("4. Temperature");

        return readInt("Choice: ");
    }

    private static String getMeasurementType(int choice) {
        return switch (choice) {
            case 1 -> "LENGTH";
            case 2 -> "WEIGHT";
            case 3 -> "VOLUME";
            case 4 -> "TEMPERATURE";
            default -> throw new IllegalArgumentException("Invalid measurement type");
        };
    }

    private static String chooseUnit(int typeChoice) {
        switch (typeChoice) {
            case 1 -> {
                System.out.println("\nLength Units:");
                System.out.println("1. FEET");
                System.out.println("2. INCHES");
                System.out.println("3. YARDS");
                System.out.println("4. CENTIMETERS");

                int u = readInt("Choose unit: ");

                return switch (u) {
                    case 1 -> "FEET";
                    case 2 -> "INCHES";
                    case 3 -> "YARDS";
                    case 4 -> "CENTIMETERS";
                    default -> throw new IllegalArgumentException("Invalid unit");
                };
            }

            case 2 -> {
                System.out.println("\nWeight Units:");
                System.out.println("1. KILOGRAM");
                System.out.println("2. GRAM");
                System.out.println("3. POUND");

                int u = readInt("Choose unit: ");

                return switch (u) {
                    case 1 -> "KILOGRAM";
                    case 2 -> "GRAM";
                    case 3 -> "POUND";
                    default -> throw new IllegalArgumentException("Invalid unit");
                };
            }

            case 3 -> {
                System.out.println("\nVolume Units:");
                System.out.println("1. LITRE");
                System.out.println("2. MILLILITRE");
                System.out.println("3. GALLON");

                int u = readInt("Choose unit: ");

                return switch (u) {
                    case 1 -> "LITRE";
                    case 2 -> "MILLILITRE";
                    case 3 -> "GALLON";
                    default -> throw new IllegalArgumentException("Invalid unit");
                };
            }

            case 4 -> {
                System.out.println("\nTemperature Units:");
                System.out.println("1. CELSIUS");
                System.out.println("2. FAHRENHEIT");
                System.out.println("3. KELVIN");

                int u = readInt("Choose unit: ");

                return switch (u) {
                    case 1 -> "CELSIUS";
                    case 2 -> "FAHRENHEIT";
                    case 3 -> "KELVIN";
                    default -> throw new IllegalArgumentException("Invalid unit");
                };
            }

            default -> throw new IllegalArgumentException("Invalid measurement type");
        }
    }

    private static int readInt(String msg) {
        System.out.print(msg);
        return scanner.nextInt();
    }

    private static double readDouble(String msg) {
        System.out.print(msg);
        return scanner.nextDouble();
    }
}