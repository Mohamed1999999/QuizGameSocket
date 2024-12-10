import java.io.*;
import java.net.*;
import java.util.*;

public class QuizServer {
    public static void main(String[] args) {
        List<QuizQuestion> questions = Arrays.asList(
                new QuizQuestion("What is the capital of France?", new String[]{"Paris", "Rome", "Berlin", "Madrid"}, "Paris"),
                new QuizQuestion("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4"),
                new QuizQuestion("What is the color of the sky?", new String[]{"Blue", "Green", "Red", "Yellow"}, "Blue")
        );

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                QuizRequest request = (QuizRequest) ois.readObject();

                if ("GET_QUIZ".equals(request.getRequestType())) {
                    oos.writeObject(new QuizResponse(questions));
                } else if ("SUBMIT_ANSWERS".equals(request.getRequestType())) {
                    List<String> answers = (List<String>) ois.readObject();
                    int score = 0;
                    StringBuilder feedback = new StringBuilder();

                    for (int i = 0; i < answers.size(); i++) {
                        String correctAnswer = questions.get(i).getCorrectAnswer();
                        if (answers.get(i).equals(correctAnswer)) {
                            score++;
                        } else {
                            feedback.append("Question ").append(i + 1).append(": Correct answer is ").append(correctAnswer).append("\n");
                        }
                    }

                    oos.writeObject(new QuizResponse(score, feedback.toString()));
                }

                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
