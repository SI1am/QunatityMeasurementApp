package QuantityMeasurementApp.com.QuantityMeasurementApp;



import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select Operation:");
        System.out.println("1. Convert Length");
        System.out.println("2. Add Lengths");
        System.out.println("3. Compare Lengths");

        int choice = sc.nextInt();

        if (choice == 1) {

            System.out.println("Enter value:");
            double value = sc.nextDouble();

//            System.out.println("Select Unit:");
            LengthUnit unit = chooseUnit(sc);

            QuantityLength quantity = new QuantityLength(value, unit);

            System.out.println("Convert to which unit?");
            LengthUnit targetUnit = chooseUnit(sc);

            QuantityLength result = quantity.convertTo(targetUnit);

            System.out.println("Converted Result: " + result);

        } else if (choice == 2) {

            System.out.println("Enter first value:");
            double value1 = sc.nextDouble();
            System.out.println("Select unit for first value:");
            LengthUnit unit1 = chooseUnit(sc);

            System.out.println("Enter second value:");
            double value2 = sc.nextDouble();
            System.out.println("Select unit for second value:");
            LengthUnit unit2 = chooseUnit(sc);

            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);

            System.out.println("Select result unit:");
            LengthUnit targetUnit = chooseUnit(sc);

            QuantityLength result = q1.add(q2, targetUnit);

            System.out.println("Addition Result: " + result);

        } else if (choice == 3) {

            System.out.println("Enter first value:");
            double value1 = sc.nextDouble();
            System.out.println("Select unit for first value:");
            LengthUnit unit1 = chooseUnit(sc);

            System.out.println("Enter second value:");
            double value2 = sc.nextDouble();
            System.out.println("Select unit for second value:");
            LengthUnit unit2 = chooseUnit(sc);

            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);

            System.out.println("Are they equal? " + q1.equals(q2));
        }

        sc.close();
    }

    private static LengthUnit chooseUnit(Scanner sc) {

        System.out.println("Choose Unit:");
        System.out.println("1. FEET");
        System.out.println("2. INCHES");
        System.out.println("3. YARDS");
        System.out.println("4. CENTIMETERS");

        int unitChoice = sc.nextInt();

        switch (unitChoice) {
            case 1:
                return LengthUnit.FEET;
            case 2:
                return LengthUnit.INCHES;
            case 3:
                return LengthUnit.YARDS;
            case 4:
                return LengthUnit.CENTIMETERS;
            default:
                throw new IllegalArgumentException("Invalid unit selection");
        }
    }
}