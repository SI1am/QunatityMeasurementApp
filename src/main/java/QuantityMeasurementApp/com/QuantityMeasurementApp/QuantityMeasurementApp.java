package QuantityMeasurementApp.com.QuantityMeasurementApp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nQuantity Measurement Tool");
            System.out.println("1. Compare Lengths");
            System.out.println("2. Convert Length");
            System.out.println("3. Add Lengths (UC6)");
            System.out.println("4. Add Lengths with Target Unit (UC7)");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String option = sc.nextLine();

            if (option.equals("5")) {
                System.out.println("Exited");
                break;
            }

            try {

                // UC5 Comparison
                if (option.equals("1")) {

                    System.out.print("\nEnter first value: ");
                    double v1 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                    Length.LengthUnit u1 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l1 = new Length(v1, u1);

                    System.out.print("\nEnter second value: ");
                    double v2 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                    Length.LengthUnit u2 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l2 = new Length(v2, u2);

                    boolean result = l1.equals(l2);

                    System.out.println("\nComparison Result:");
                    System.out.println("Input: " + l1 + " and " + l2);
                    System.out.println("Output: Equal (" + result + ")");
                }

                // UC5 Conversion
                else if (option.equals("2")) {

                    System.out.print("\nEnter value to convert: ");
                    double value = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter source unit: ");
                    Length.LengthUnit source = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    System.out.print("Enter target unit: ");
                    Length.LengthUnit target = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    double result = Length.convert(value, source, target);

                    System.out.println("\nConversion Result:");
                    System.out.println("Input: " + value + " " + source.name().toLowerCase());
                    System.out.println("Output: " + result + " " + target.name().toLowerCase());
                }

                // UC6 Addition
                else if (option.equals("3")) {

                    System.out.print("\nEnter first value: ");
                    double v1 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit: ");
                    Length.LengthUnit u1 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l1 = new Length(v1, u1);

                    System.out.print("\nEnter second value: ");
                    double v2 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit: ");
                    Length.LengthUnit u2 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l2 = new Length(v2, u2);

                    Length result = l1.add(l2);

                    System.out.println("\nAddition Result (UC6):");
                    System.out.println("Input: " + l1 + " + " + l2);
                    System.out.println("Output: " + result);
                }

                // UC7 Addition with target unit
                else if (option.equals("4")) {

                    System.out.print("\nEnter first value: ");
                    double v1 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit: ");
                    Length.LengthUnit u1 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l1 = new Length(v1, u1);

                    System.out.print("\nEnter second value: ");
                    double v2 = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter unit: ");
                    Length.LengthUnit u2 = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length l2 = new Length(v2, u2);

                    System.out.print("\nEnter target unit: ");
                    Length.LengthUnit target = Length.LengthUnit.valueOf(
                            sc.nextLine().trim().toUpperCase());

                    Length result = l1.add(l2, target);

                    System.out.println("\nAddition Result (UC7):");
                    System.out.println("Input: " + l1 + " + " + l2);
                    System.out.println("Target Unit: " + target.name().toLowerCase());
                    System.out.println("Output: " + result);
                }

                else {
                    System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}