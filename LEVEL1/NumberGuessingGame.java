
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int number = random.nextInt(100) + 1;
        int attempts = 7;

        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100.");
        System.out.println("You have 7 attempts.");

        for (int i = 1; i <= attempts; i++) {

            System.out.print("\nAttempt " + i + ": ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                i--;
                continue;
            }

            int guess = sc.nextInt();

            if (guess == number) {
                System.out.println("Congratulations! Correct guess.");
                break;
            } else if (guess > number) {
                System.out.println("Too High!");
            } else {
                System.out.println("Too Low!");
            }

            if (i == attempts) {
                System.out.println("Game Over!");
                System.out.println("The correct number was: " + number);
            }
        }

        sc.close();
    }
}
