package QuantityMeasurementApp.com.QuantityMeasurementApp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        System.out.println("=== Quantity Measurement App ===");

        System.out.println("Select Category:");
        System.out.println("1. Length");
        System.out.println("2. Weight");

        int category = sc.nextInt();

        System.out.println("\nSelect Operation:");
        System.out.println("1. Convert");
        System.out.println("2. Add");
        System.out.println("3. Compare");

        int operation = sc.nextInt();

        if (category == 1) {
            handleLength(sc, operation);
        }
        else if (category == 2) {
            handleWeight(sc, operation);
        }
        else {
            System.out.println("Invalid category.");
        }

        sc.close();
    }



    private static void handleLength(Scanner sc, int operation) {

        if (operation == 1) {

            System.out.println("Enter value:");
            double value = sc.nextDouble();

            LengthUnit unit = chooseLengthUnit(sc);

            QuantityLength q = new QuantityLength(value, unit);

            System.out.println("Convert to:");
            LengthUnit target = chooseLengthUnit(sc);

            System.out.println("Result: " + q.convertTo(target));
        }

        else if (operation == 2) {

            System.out.println("Enter first value:");
            double v1 = sc.nextDouble();
            LengthUnit u1 = chooseLengthUnit(sc);

            System.out.println("Enter second value:");
            double v2 = sc.nextDouble();
            LengthUnit u2 = chooseLengthUnit(sc);

            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);

            System.out.println("Result unit:");
            LengthUnit target = chooseLengthUnit(sc);

            System.out.println("Addition Result: " + q1.add(q2, target));
        }

        else if (operation == 3) {

            System.out.println("Enter first value:");
            double v1 = sc.nextDouble();
            LengthUnit u1 = chooseLengthUnit(sc);

            System.out.println("Enter second value:");
            double v2 = sc.nextDouble();
            LengthUnit u2 = chooseLengthUnit(sc);

            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);

            System.out.println("Are equal? " + q1.equals(q2));
        }
    }


    private static void handleWeight(Scanner sc, int operation) {

        if (operation == 1) {

            System.out.println("Enter value:");
            double value = sc.nextDouble();

            WeightUnit unit = chooseWeightUnit(sc);

            QuantityWeight q = new QuantityWeight(value, unit);

            System.out.println("Convert to:");
            WeightUnit target = chooseWeightUnit(sc);

            System.out.println("Result: " + q.convertTo(target));
        }

        else if (operation == 2) {

            System.out.println("Enter first value:");
            double v1 = sc.nextDouble();
            WeightUnit u1 = chooseWeightUnit(sc);

            System.out.println("Enter second value:");
            double v2 = sc.nextDouble();
            WeightUnit u2 = chooseWeightUnit(sc);

            QuantityWeight q1 = new QuantityWeight(v1, u1);
            QuantityWeight q2 = new QuantityWeight(v2, u2);

            System.out.println("Result unit:");
            WeightUnit target = chooseWeightUnit(sc);

            System.out.println("Addition Result: " + q1.add(q2, target));
        }

        else if (operation == 3) {

            System.out.println("Enter first value:");
            double v1 = sc.nextDouble();
            WeightUnit u1 = chooseWeightUnit(sc);

            System.out.println("Enter second value:");
            double v2 = sc.nextDouble();
            WeightUnit u2 = chooseWeightUnit(sc);

            QuantityWeight q1 = new QuantityWeight(v1, u1);
            QuantityWeight q2 = new QuantityWeight(v2, u2);

            System.out.println("Are equal? " + q1.equals(q2));
        }
    }



    private static LengthUnit chooseLengthUnit(Scanner sc) {

        System.out.println("Choose Length Unit:");
        System.out.println("1. FEET");
        System.out.println("2. INCHES");
        System.out.println("3. YARDS");
        System.out.println("4. CENTIMETERS");

        int choice = sc.nextInt();

        switch (choice) {
            case 1: return LengthUnit.FEET;
            case 2: return LengthUnit.INCHES;
            case 3: return LengthUnit.YARDS;
            case 4: return LengthUnit.CENTIMETERS;
            default: throw new IllegalArgumentException("Invalid Length Unit");
        }
    }

    private static WeightUnit chooseWeightUnit(Scanner sc) {

        System.out.println("Choose Weight Unit:");
        System.out.println("1. KILOGRAM");
        System.out.println("2. GRAM");
        System.out.println("3. POUND");

        int choice = sc.nextInt();

        switch (choice) {
            case 1: return WeightUnit.KILOGRAM;
            case 2: return WeightUnit.GRAM;
            case 3: return WeightUnit.POUND;
            default: throw new IllegalArgumentException("Invalid Weight Unit");
        }
    }
}