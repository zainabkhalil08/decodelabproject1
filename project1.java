import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class NumberGuessingGame {

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 7;

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     WELCOME TO NUMBER GUESSER    ");
        System.out.println("=================================");

        int totalScore = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed++;
            int pointsEarned = playRound();
            totalScore += pointsEarned;

            System.out.println("\nRound " + roundsPlayed + " score: " + pointsEarned);
            System.out.println("Total score so far: " + totalScore);

            playAgain = askYesNo("\nDo you want to play another round? (y/n): ");
        }

        System.out.println("\n=================================");
        System.out.println("           GAME OVER              ");
        System.out.println("Rounds played : " + roundsPlayed);
        System.out.println("Final score   : " + totalScore);
        System.out.println("=================================");
        System.out.println("Thanks for playing!");

        scanner.close();
    }

   
    private static int playRound() {
        int secretNumber = MIN_RANGE + random.nextInt(MAX_RANGE - MIN_RANGE + 1);
        int attemptsUsed = 0;
        boolean guessedCorrectly = false;

        System.out.println("\nI'm thinking of a number between "
                + MIN_RANGE + " and " + MAX_RANGE + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

        while (attemptsUsed < MAX_ATTEMPTS && !guessedCorrectly) {
            int remaining = MAX_ATTEMPTS - attemptsUsed;
            int guess = readIntInput("Attempt " + (attemptsUsed + 1)
                    + "/" + MAX_ATTEMPTS + " - Enter your guess: ");

            attemptsUsed++;

            if (guess < MIN_RANGE || guess > MAX_RANGE) {
                System.out.println("Please guess a number within the range "
                        + MIN_RANGE + "-" + MAX_RANGE + ".");
                attemptsUsed--; 
                continue;
            }

            if (guess == secretNumber) {
                guessedCorrectly = true;
                System.out.println("Correct! The number was " + secretNumber + ".");
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
                if (attemptsUsed < MAX_ATTEMPTS) {
                    System.out.println((remaining - 1) + " attempt(s) left.");
                }
            } else {
                System.out.println("Too high!");
                if (attemptsUsed < MAX_ATTEMPTS) {
                    System.out.println((remaining - 1) + " attempt(s) left.");
                }
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Out of attempts! The number was " + secretNumber + ".");
            return 0;
        }

      
        return Math.max(0, (MAX_ATTEMPTS - attemptsUsed + 1) * 10);
    }

   
    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

   
    private static boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("y") || response.equals("yes")) {
                    return true;
                } else if (response.equals("n") || response.equals("no")) {
                    return false;
                } else {
                    System.out.println("Please answer with 'y' or 'n'.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong reading input. Try again.");
            }
        }
    }
}
