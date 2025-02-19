import java.util.*;
import java.util.concurrent.*;

class QuizQuestion {
    String question;
    List<String> options;
    int correctAnswer;

    public QuizQuestion(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private Scanner scanner;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.score = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?", Arrays.asList("1. Berlin", "2. Madrid", "3. Paris", "4. Rome"), 3));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", Arrays.asList("1. Earth", "2. Mars", "3. Jupiter", "4. Venus"), 2));
        questions.add(new QuizQuestion("What is the largest mammal?", Arrays.asList("1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Hippo"), 2));
    }

    public void startQuiz() {
        for (QuizQuestion question : questions) {
            askQuestion(question);
        }
        displayResults();
    }

    private void askQuestion(QuizQuestion question) {
        System.out.println(question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.print("Enter your choice (1-4) within 10 seconds: ");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> scanner.nextInt());

        try {
            int answer = future.get(10, TimeUnit.SECONDS);
            if (answer == question.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong answer.");
            }
        } catch (TimeoutException e) {
            System.out.println("\nTime's up! Moving to the next question.");
        } catch (Exception e) {
            System.out.println("\nInvalid input.");
        } finally {
            executor.shutdownNow();
        }
    }

    private void displayResults() {
        System.out.println(" \nQuiz Over! Your score: " + score + "/" + questions.size());
    }
}

public class QuizGame {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}
