import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Question> questions = new ArrayList<>();
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static Random random = new Random();
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have 10 seconds for each question.");
        System.out.println("Type your answer and press Enter.");

        displayNextQuestion();

        // Keep the program running
        while (currentQuestionIndex < questions.size()) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up for this question!");
                    displayNextQuestion();
                }
            };

            timer = new Timer();
            timer.schedule(task, 10000); // Set the timer for 10 seconds

            String answer = scanner.nextLine();
            checkAnswer(answer);

            timer.cancel(); // Cancel the current timer

            if (currentQuestionIndex < questions.size()) {
                displayNextQuestion();
            }
        }

        displayFinalScore();
    }

    private static void initializeQuestions() {
        // Add your questions and answers here
        questions.add(new Question("What is the capital of France?", "Paris"));
        questions.add(new Question("Which planet is known as the Red Planet?", "Mars"));
        questions.add(new Question("What is the largest mammal?", "Blue Whale"));
        questions.add(new Question("What is the chemical symbol for gold?", "Au"));
        questions.add(new Question("Who wrote the play 'Romeo and Juliet'?", "William Shakespeare"));
        questions.add(new Question("What is the largest planet in our solar system?", "Jupiter"));
        questions.add(new Question("Which gas do plants use for photosynthesis?", "Carbon Dioxide"));
        questions.add(new Question("What is the smallest prime number?", "2"));
        questions.add(new Question("What is the largest bone in the human body?", "Femur"));
        questions.add(new Question("What is the chemical symbol for water?", "H2O"));
        Collections.shuffle(questions, random);
    }

    private static void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.questionText);
        }
    }

    private static void checkAnswer(String userAnswer) {
        Question question = questions.get(currentQuestionIndex);
        if (userAnswer.equalsIgnoreCase(question.correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + question.correctAnswer);
        }
        currentQuestionIndex++;
    }

    private static void displayFinalScore() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + " out of " + questions.size());
    }
}
