import java.util.Random;
import java.util.Scanner;


public class Number_game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        System.out.println("Welcome to the Guess the Number game!");

        while (playAgain) {

            int min = 1;
            int max = 100;
            int generatedNumber = random.nextInt(max - min + 1) + min;


            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have generated a random number between " + min + " and " + max + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > generatedNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number: " + generatedNumber);
                    guessedCorrectly = true;
                    score += (maxAttempts - attempts + 1);
                    break;
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you're out of attempts! The correct number was: " + generatedNumber);
            }

            System.out.println("Your current score: " + score);


            System.out.print("\nDo you want to play another round? (yes/no): ");
            scanner.nextLine();
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame over! Your final score: " + score);
        System.out.println("Thank you for playing!");

        scanner.close();
    }
}

