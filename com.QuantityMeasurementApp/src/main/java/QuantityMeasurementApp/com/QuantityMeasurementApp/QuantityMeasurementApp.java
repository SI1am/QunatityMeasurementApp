package QuantityMeasurementApp.com.QuantityMeasurementApp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean compareFeet(double value1, double value2) {
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);
        return f1.equals(f2);
    }

    public static boolean compareInches(double value1, double value2) {
        Inches i1 = new Inches(value1);
        Inches i2 = new Inches(value2);
        return i1.equals(i2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the unit to compare:");
            System.out.println("1. Feet");
            System.out.println("2. Inches");
            System.out.println("3. Exit");
            System.out.print("Enter option (1-3): ");

            String option = sc.nextLine();

            if (option.equals("3")) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter first value: ");
            double value1 = sc.nextDouble();

            System.out.print("Enter second value: ");
            double value2 = sc.nextDouble();

            try {
                
                boolean result;

                switch (option) {
                    case "1":
                        result = compareFeet(value1, value2);
                        System.out.println("Input: " + value1 + " ft and " + value2 + " ft");
                        System.out.println("Output: Equal (" + result + ")");
                        break;

                    case "2":
                        result = compareInches(value1, value2);
                        System.out.println("Input: " + value1 + " inch and " + value2 + " inch");
                        System.out.println("Output: Equal (" + result + ")");
                        break;

                    default:
                        System.out.println("Invalid option. Please enter 1, 2, or 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input. Please enter valid numbers.");
            }

            System.out.println();
        }

        sc.close();
    }
}
