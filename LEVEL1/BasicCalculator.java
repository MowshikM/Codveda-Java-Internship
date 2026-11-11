
import java.util.Scanner;

class Calculator {

    double add(double a, double b) {
        return a + b;
    }

    double subtract(double a, double b) {
        return a - b;
    }

    double multiply(double a, double b) {
        return a * b;
    }

    double divide(double a, double b) {
        return a / b;
    }
}

public class BasicCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Calculator cal = new Calculator();

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        System.out.println("\n1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        System.out.print("Choose operation: ");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.println("Result: " + cal.add(a, b));
                break;

            case 2:
                System.out.println("Result: " + cal.subtract(a, b));
                break;

            case 3:
                System.out.println("Result: " + cal.multiply(a, b));
                break;

            case 4:
                if (b == 0) {
                    System.out.println("Cannot divide by zero!");
                } else {
                    System.out.println("Result: " + cal.divide(a, b));
                }
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}