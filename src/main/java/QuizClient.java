import java.io.*;
import java.net.*;
import java.util.*;

public class QuizClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the server.");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(new QuizRequest("GET_QUIZ"));

            QuizResponse response = (QuizResponse) ois.readObject();
            List<QuizQuestion> questions = response.getQuestions();

            List<String> answers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            for (QuizQuestion question : questions) {
                System.out.println(question.getQuestionText());
                String[] options = question.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                System.out.print("Your answer: ");
                int choice = scanner.nextInt();
                answers.add(options[choice - 1]);
            }

            oos.writeObject(new QuizRequest("SUBMIT_ANSWERS"));
            oos.writeObject(answers);

            response = (QuizResponse) ois.readObject();
            System.out.println("Your score: " + response.getScore());
            System.out.println("Feedback:");
            System.out.println(response.getFeedback());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
