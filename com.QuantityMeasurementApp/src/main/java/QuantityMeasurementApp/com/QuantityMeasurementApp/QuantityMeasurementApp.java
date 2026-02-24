package QuantityMeasurementApp.com.QuantityMeasurementApp;

import java.util.Scanner;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Length Comparison Tool");
            System.out.println("1. Compare Lengths");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            String option = sc.nextLine();

            if (option.equals("2")) {
                System.out.println("Exited");
                break;
            }

            try {


                System.out.print("\nEnter first value: ");
                double v1 = Double.parseDouble(sc.nextLine());

                System.out.print("Enter unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                Length.LengthUnit u1 = Length.LengthUnit.valueOf(
                        sc.nextLine().trim().toUpperCase()
                );

                Length l1 = new Length(v1, u1);

                
                System.out.print("\nEnter second value: ");
                double v2 = Double.parseDouble(sc.nextLine());

                System.out.print("Enter unit (FEET/INCHES/YARDS/CENTIMETERS): ");
                Length.LengthUnit u2 = Length.LengthUnit.valueOf(
                        sc.nextLine().trim().toUpperCase()
                );

                Length l2 = new Length(v2, u2);


                boolean result = l1.equals(l2);

                System.out.println("Comparison Result:");
                System.out.println("Input: " + l1 + " and " + l2);
                System.out.println("Output: Equal (" + result + ")");

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid unit. Please enter FEET or INCHES.");
            }
        }

        sc.close();
    }
}